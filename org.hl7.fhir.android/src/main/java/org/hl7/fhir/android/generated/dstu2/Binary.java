package org.hl7.fhir.android.generated.dstu2;

/*-
 * #%L
 * org.hl7.fhir.dstu2
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
/*
  Copyright (c) 2011+, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, 
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this 
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, 
     this list of conditions and the following disclaimer in the documentation 
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
  POSSIBILITY OF SUCH DAMAGE.
  
*/
// Generated on Wed, Jul 13, 2016 05:32+1000 for FHIR v1.0.2
import java.util.List;
import org.hl7.fhir.instance.model.api.IBaseBinary;
import org.hl7.fhir.exceptions.FHIRException;

/**
 * A binary resource can contain any content, whether text, image, pdf, zip archive, etc.
 */
public class Binary extends BaseBinary implements IBaseBinary {

    /**
     * MimeType of the binary content represented as a standard MimeType (BCP 13).
     */
    protected CodeType contentType;

    /**
     * The actual content, base64 encoded.
     */
    protected Base64BinaryType content;

    private static final long serialVersionUID = 974764407L;

    /*
   * Constructor
   */
    public Binary() {
        super();
    }

    /*
   * Constructor
   */
    public Binary(CodeType contentType, Base64BinaryType content) {
        super();
        this.contentType = contentType;
        this.content = content;
    }

    /**
     * @return {@link #contentType} (MimeType of the binary content represented as a standard MimeType (BCP 13).). This is the underlying object with id, value and extensions. The accessor "getContentType" gives direct access to the value
     */
    public CodeType getContentTypeElement() {
        if (this.contentType == null)
            if (Configuration.errorOnAutoCreate())
                throw new Error("Attempt to auto-create Binary.contentType");
            else if (Configuration.doAutoCreate())
                // bb
                this.contentType = new CodeType();
        return this.contentType;
    }

    public boolean hasContentTypeElement() {
        return this.contentType != null && !this.contentType.isEmpty();
    }

    public boolean hasContentType() {
        return this.contentType != null && !this.contentType.isEmpty();
    }

    /**
     * @param value {@link #contentType} (MimeType of the binary content represented as a standard MimeType (BCP 13).). This is the underlying object with id, value and extensions. The accessor "getContentType" gives direct access to the value
     */
    public Binary setContentTypeElement(CodeType value) {
        this.contentType = value;
        return this;
    }

    /**
     * @return MimeType of the binary content represented as a standard MimeType (BCP 13).
     */
    public String getContentType() {
        return this.contentType == null ? null : this.contentType.getValue();
    }

    /**
     * @param value MimeType of the binary content represented as a standard MimeType (BCP 13).
     */
    public Binary setContentType(String value) {
        if (this.contentType == null)
            this.contentType = new CodeType();
        this.contentType.setValue(value);
        return this;
    }

    /**
     * @return {@link #content} (The actual content, base64 encoded.). This is the underlying object with id, value and extensions. The accessor "getContent" gives direct access to the value
     */
    public Base64BinaryType getContentElement() {
        if (this.content == null)
            if (Configuration.errorOnAutoCreate())
                throw new Error("Attempt to auto-create Binary.content");
            else if (Configuration.doAutoCreate())
                // bb
                this.content = new Base64BinaryType();
        return this.content;
    }

    public boolean hasContentElement() {
        return this.content != null && !this.content.isEmpty();
    }

    public boolean hasContent() {
        return this.content != null && !this.content.isEmpty();
    }

    /**
     * @param value {@link #content} (The actual content, base64 encoded.). This is the underlying object with id, value and extensions. The accessor "getContent" gives direct access to the value
     */
    public Binary setContentElement(Base64BinaryType value) {
        this.content = value;
        return this;
    }

    /**
     * @return The actual content, base64 encoded.
     */
    public byte[] getContent() {
        return this.content == null ? null : this.content.getValue();
    }

    /**
     * @param value The actual content, base64 encoded.
     */
    public Binary setContent(byte[] value) {
        if (this.content == null)
            this.content = new Base64BinaryType();
        this.content.setValue(value);
        return this;
    }

    protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("contentType", "code", "MimeType of the binary content represented as a standard MimeType (BCP 13).", 0, java.lang.Integer.MAX_VALUE, contentType));
        childrenList.add(new Property("content", "base64Binary", "The actual content, base64 encoded.", 0, java.lang.Integer.MAX_VALUE, content));
    }

    public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("contentType"))
            // CodeType
            this.contentType = castToCode(value);
        else if (name.equals("content"))
            // Base64BinaryType
            this.content = castToBase64Binary(value);
        else
            super.setProperty(name, value);
    }

    public Base addChild(String name) throws FHIRException {
        if (name.equals("contentType")) {
            throw new FHIRException("Cannot call addChild on a primitive type Binary.contentType");
        } else if (name.equals("content")) {
            throw new FHIRException("Cannot call addChild on a primitive type Binary.content");
        } else
            return super.addChild(name);
    }

    public String fhirType() {
        return "Binary";
    }

    public Binary copy() {
        Binary dst = new Binary();
        copyValues(dst);
        dst.contentType = contentType == null ? null : contentType.copy();
        dst.content = content == null ? null : content.copy();
        return dst;
    }

    protected Binary typedCopy() {
        return copy();
    }

    public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
            return false;
        if (!(other instanceof Binary))
            return false;
        Binary o = (Binary) other;
        return compareDeep(contentType, o.contentType, true) && compareDeep(content, o.content, true);
    }

    public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
            return false;
        if (!(other instanceof Binary))
            return false;
        Binary o = (Binary) other;
        return compareValues(contentType, o.contentType, true) && compareValues(content, o.content, true);
    }

    public boolean isEmpty() {
        return super.isEmpty() && (contentType == null || contentType.isEmpty()) && (content == null || content.isEmpty());
    }

    public ResourceType getResourceType() {
        return ResourceType.Binary;
    }

    public static final String SP_CONTENTTYPE = "contenttype";
}