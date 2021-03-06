package org.hl7.fhir.r5.terminologies;

/*-
 * #%L
 * org.hl7.fhir.r5
 * %%
 * Copyright (C) 2014 - 2019 Health Level 7
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.model.CanonicalType;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r5.model.Identifier;
import org.hl7.fhir.r5.model.Meta;
import org.hl7.fhir.r5.model.UriType;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r5.model.Enumerations.FilterOperator;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.Utilities;

public class ValueSetUtilities {

  public static ValueSet makeShareable(ValueSet vs) {
    if (!vs.hasMeta())
      vs.setMeta(new Meta());
    for (UriType t : vs.getMeta().getProfile()) 
      if (t.getValue().equals("http://hl7.org/fhir/StructureDefinition/shareablevalueset"))
        return vs;
    vs.getMeta().getProfile().add(new CanonicalType("http://hl7.org/fhir/StructureDefinition/shareablevalueset"));
    return vs;
  }

  public static void checkShareable(ValueSet vs) {
    if (!vs.hasMeta())
      throw new Error("ValueSet "+vs.getUrl()+" is not shareable");
    for (UriType t : vs.getMeta().getProfile()) {
      if (t.getValue().equals("http://hl7.org/fhir/StructureDefinition/shareablevalueset"))
        return;
    }
    throw new Error("ValueSet "+vs.getUrl()+" is not shareable");    
  }

  public static boolean hasOID(ValueSet vs) {
    return getOID(vs) != null;
  }

  public static String getOID(ValueSet vs) {
    for (Identifier id : vs.getIdentifier()) {
      if ("urn:ietf:rfc:3986".equals(id.getSystem()) && id.hasValue() && id.getValue().startsWith("urn:oid:"))
        return id.getValue().substring(8);
    }
    return null;
  }

  public static void setOID(ValueSet vs, String oid) {
    if (!oid.startsWith("urn:oid:"))
      oid = "urn:oid:" + oid;
    for (Identifier id : vs.getIdentifier()) {
      if ("urn:ietf:rfc:3986".equals(id.getSystem()) && id.hasValue() && id.getValue().startsWith("urn:oid:")) {
        id.setValue(oid);
        return;
      }
    }
    vs.addIdentifier().setSystem("urn:ietf:rfc:3986").setValue(oid);
  }

  public static void markStatus(ValueSet vs, String wg, StandardsStatus status, String pckage, String fmm, IWorkerContext context, String normativeVersion) throws FHIRException {
    if (vs.hasUserData("external.url"))
      return;
    
    if (wg != null) {
      if (!ToolingExtensions.hasExtension(vs, ToolingExtensions.EXT_WORKGROUP) || 
          (!Utilities.existsInList(ToolingExtensions.readStringExtension(vs, ToolingExtensions.EXT_WORKGROUP), "fhir", "vocab") && Utilities.existsInList(wg, "fhir", "vocab"))) {
        ToolingExtensions.setCodeExtension(vs, ToolingExtensions.EXT_WORKGROUP, wg);
      }
    }
    if (status != null) {
      StandardsStatus ss = ToolingExtensions.getStandardsStatus(vs);
      if (ss == null || ss.isLowerThan(status)) 
        ToolingExtensions.setStandardsStatus(vs, status, normativeVersion);
      if (pckage != null) {
        if (!vs.hasUserData("ballot.package"))        
          vs.setUserData("ballot.package", pckage);
        else if (!pckage.equals(vs.getUserString("ballot.package")))
          if (!"infrastructure".equals(vs.getUserString("ballot.package")))
          System.out.println("Value Set "+vs.getUrl()+": ownership clash "+pckage+" vs "+vs.getUserString("ballot.package"));
      }
      if (status == StandardsStatus.NORMATIVE) {
        vs.setExperimental(false);
        vs.setStatus(PublicationStatus.ACTIVE);
      }
    }
    if (fmm != null) {
      String sfmm = ToolingExtensions.readStringExtension(vs, ToolingExtensions.EXT_FMM_LEVEL);
      if (Utilities.noString(sfmm) || Integer.parseInt(sfmm) < Integer.parseInt(fmm)) 
        ToolingExtensions.setIntegerExtension(vs, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(fmm));
    }
    if (vs.hasUserData("cs"))
      CodeSystemUtilities.markStatus((CodeSystem) vs.getUserData("cs"), wg, status, pckage, fmm, normativeVersion);
    else if (status == StandardsStatus.NORMATIVE && context != null) {
      for (ConceptSetComponent csc : vs.getCompose().getInclude()) {
        if (csc.hasSystem()) {
          CodeSystem cs = context.fetchCodeSystem(csc.getSystem());
          if (cs != null) {
            CodeSystemUtilities.markStatus(cs, wg, status, pckage, fmm, normativeVersion);
          }
        }
      }
    }
  }

  private static int ssval(String status) {
    if ("Draft".equals("status")) 
      return 1;
    if ("Informative".equals("status")) 
      return 2;
    if ("External".equals("status")) 
      return 3;
    if ("Trial Use".equals("status")) 
      return 3;
    if ("Normative".equals("status")) 
      return 4;
    return -1;
  }

  public static ValueSet generateImplicitValueSet(String uri) {
    if (uri.startsWith("http://snomed.info/sct"))
      return generateImplicitSnomedValueSet(uri);
    if (uri.startsWith("http://loinc.org/vs"))
      return generateImplicitLoincValueSet(uri);
    if (uri.equals("http://hl7.org/fhir/ValueSet/mimetypes")) {
      return generateImplicitMimetypesValueSet(uri);
    }
    return null;
  }

  private static ValueSet generateImplicitMimetypesValueSet(String theUri) {
    ValueSet valueSet = new ValueSet();
    valueSet.setUrl(theUri);
    valueSet.setDescription("This value set includes all possible codes from BCP-13 (http://tools.ietf.org/html/bcp13)");
    valueSet.getCompose()
      .addInclude().setSystem("urn:ietf:bcp:13");
    return valueSet;
  }

  private static ValueSet generateImplicitLoincValueSet(String uri) {
    if ("http://loinc.org/vs".equals(uri))
      return makeLoincValueSet();
    if (uri.startsWith("http://loinc.org/vs/LL"))
      return makeAnswerList(makeLoincValueSet(), uri);
    return null;
  }

  private static ValueSet makeAnswerList(ValueSet vs, String uri) {
    vs.setUrl(uri);
    String c = uri.substring(20);
    vs.setName("LOINCAnswers"+c);
    vs.setTitle("LOINC Answer Codes for "+c);
    vs.getCompose().getIncludeFirstRep().addFilter().setProperty("LIST").setOp(FilterOperator.EQUAL).setValue(c);
    return vs;
  }

  private static ValueSet makeLoincValueSet() {
    ValueSet vs = new ValueSet();
    vs.setUrl("http://loinc.org/vs");
    vs.setName("LOINCCodes");
    vs.setTitle("All LOINC codes");
    vs.setCopyright("This content LOINC® is copyright © 1995 Regenstrief Institute, Inc. and the LOINC Committee, and available at no cost under the license at http://loinc.org/terms-of-use");
    vs.setStatus(PublicationStatus.ACTIVE);
    vs.getCompose().addInclude().setSystem("http://loinc.org");
    return vs;
  }

  private static ValueSet generateImplicitSnomedValueSet(String uri) {
    if ("http://snomed.info/sct?fhir_vs".equals(uri))
      return makeImplicitSnomedValueSet(uri);
    return null;
  }

  private static ValueSet makeImplicitSnomedValueSet(String uri) {
    ValueSet vs = new ValueSet();
    vs.setUrl(uri);
    vs.setName("SCTValueSet");
    vs.setTitle("SCT ValueSet");
    vs.setDescription("All SNOMED CT Concepts");
    vs.setCopyright("This value set includes content from SNOMED CT, which is copyright © 2002+ International Health Terminology Standards Development Organisation (SNOMED International), and distributed by agreement between SNOMED International and HL7. Implementer use of SNOMED CT is not covered by this agreement");
    vs.setStatus(PublicationStatus.ACTIVE);
    vs.getCompose().addInclude().setSystem("http://snomed.info/sct");
    return vs;
  }

}
