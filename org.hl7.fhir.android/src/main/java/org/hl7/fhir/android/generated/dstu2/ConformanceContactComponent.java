package org.hl7.fhir.android.generated.dstu2;

// Generated on Wed, Jul 13, 2016 05:32+1000 for FHIR v1.0.2
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hl7.fhir.dstu2.model.Enumerations.ConformanceResourceStatus;
import org.hl7.fhir.dstu2.model.Enumerations.ConformanceResourceStatusEnumFactory;
import org.hl7.fhir.dstu2.model.Enumerations.SearchParamType;
import org.hl7.fhir.dstu2.model.Enumerations.SearchParamTypeEnumFactory;
import org.hl7.fhir.instance.model.api.IBaseBackboneElement;
import org.hl7.fhir.instance.model.api.IBaseConformance;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.TransactionMode;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.TransactionModeEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.ConditionalDeleteStatus;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.ConditionalDeleteStatusEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.SearchModifierCode;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.SearchModifierCodeEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.SystemRestfulInteraction;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.SystemRestfulInteractionEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.UnknownContentCode;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.UnknownContentCodeEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.MessageSignificanceCategory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.MessageSignificanceCategoryEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.ConformanceEventMode;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.ConformanceEventModeEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.TypeRestfulInteraction;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.TypeRestfulInteractionEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.RestfulConformanceMode;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.RestfulConformanceModeEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.ResourceVersionPolicy;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.ResourceVersionPolicyEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.DocumentMode;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.DocumentModeEnumFactory;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.ConformanceStatementKind;
import org.hl7.fhir.android.generated.dstu2.ConformanceEnum.ConformanceStatementKindEnumFactory;

public class ConformanceContactComponent extends BackboneElement implements IBaseBackboneElement {

    /**
     * The name of an individual to contact regarding the conformance.
     */
    protected StringType name;

    /**
     * Contact details for individual (if a name was provided) or the publisher.
     */
    protected List<ContactPoint> telecom;

    private static final long serialVersionUID = -1179697803L;

    /*
     * Constructor
     */
    public ConformanceContactComponent() {
        super();
    }

    /**
     * @return {@link #name} (The name of an individual to contact regarding the conformance.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public StringType getNameElement() {
        if (this.name == null)
            if (Configuration.errorOnAutoCreate())
                throw new Error("Attempt to auto-create ConformanceContactComponent.name");
            else if (Configuration.doAutoCreate())
                // bb
                this.name = new StringType();
        return this.name;
    }

    public boolean hasNameElement() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean hasName() {
        return this.name != null && !this.name.isEmpty();
    }

    /**
     * @param value {@link #name} (The name of an individual to contact regarding the conformance.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public ConformanceContactComponent setNameElement(StringType value) {
        this.name = value;
        return this;
    }

    /**
     * @return The name of an individual to contact regarding the conformance.
     */
    public String getName() {
        return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value The name of an individual to contact regarding the conformance.
     */
    public ConformanceContactComponent setName(String value) {
        if (Utilities.noString(value))
            this.name = null;
        else {
            if (this.name == null)
                this.name = new StringType();
            this.name.setValue(value);
        }
        return this;
    }

    /**
     * @return {@link #telecom} (Contact details for individual (if a name was provided) or the publisher.)
     */
    public List<ContactPoint> getTelecom() {
        if (this.telecom == null)
            this.telecom = new ArrayList<ContactPoint>();
        return this.telecom;
    }

    public boolean hasTelecom() {
        if (this.telecom == null)
            return false;
        for (ContactPoint item : this.telecom) if (!item.isEmpty())
            return true;
        return false;
    }

    // syntactic sugar
    public ContactPoint addTelecom() {
        // 3
        ContactPoint t = new ContactPoint();
        if (this.telecom == null)
            this.telecom = new ArrayList<ContactPoint>();
        this.telecom.add(t);
        return t;
    }

    // syntactic sugar
    public ConformanceContactComponent addTelecom(ContactPoint t) {
        // 3
        if (t == null)
            return this;
        if (this.telecom == null)
            this.telecom = new ArrayList<ContactPoint>();
        this.telecom.add(t);
        return this;
    }

    protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("name", "string", "The name of an individual to contact regarding the conformance.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("telecom", "ContactPoint", "Contact details for individual (if a name was provided) or the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
    }

    public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("name"))
            // StringType
            this.name = castToString(value);
        else if (name.equals("telecom"))
            this.getTelecom().add(castToContactPoint(value));
        else
            super.setProperty(name, value);
    }

    public Base addChild(String name) throws FHIRException {
        if (name.equals("name")) {
            throw new FHIRException("Cannot call addChild on a primitive type Conformance.name");
        } else if (name.equals("telecom")) {
            return addTelecom();
        } else
            return super.addChild(name);
    }

    public ConformanceContactComponent copy() {
        ConformanceContactComponent dst = new ConformanceContactComponent();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        if (telecom != null) {
            dst.telecom = new ArrayList<ContactPoint>();
            for (ContactPoint i : telecom) dst.telecom.add(i.copy());
        }
        ;
        return dst;
    }

    public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
            return false;
        if (!(other instanceof ConformanceContactComponent))
            return false;
        ConformanceContactComponent o = (ConformanceContactComponent) other;
        return compareDeep(name, o.name, true) && compareDeep(telecom, o.telecom, true);
    }

    public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
            return false;
        if (!(other instanceof ConformanceContactComponent))
            return false;
        ConformanceContactComponent o = (ConformanceContactComponent) other;
        return compareValues(name, o.name, true);
    }

    public boolean isEmpty() {
        return super.isEmpty() && (name == null || name.isEmpty()) && (telecom == null || telecom.isEmpty());
    }

    public String fhirType() {
        return "Conformance.contact";
    }
}