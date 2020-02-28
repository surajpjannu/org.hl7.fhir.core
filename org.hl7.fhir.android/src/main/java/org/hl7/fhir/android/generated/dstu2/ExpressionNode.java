package org.hl7.fhir.android.generated.dstu2;

import org.hl7.fhir.android.generated.dstu2.ExpressionNodeEnum.Function;
import org.hl7.fhir.android.generated.dstu2.ExpressionNodeEnum.Kind;
import org.hl7.fhir.android.generated.dstu2.ExpressionNodeEnum.Operation;
import org.hl7.fhir.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

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
public class ExpressionNode {

    // the expression will have one of either name or constant
    private String uniqueId;

    private Kind kind;

    private String name;

    private String constant;

    private Function function;

    // will be created if there is a function
    private List<ExpressionNode> parameters;

    private ExpressionNode inner;

    private ExpressionNode group;

    private Operation operation;

    // a proximal operation is the first in the sequence of operations. This is significant when evaluating the outcomes
    private boolean proximal;

    private ExpressionNode opNext;

    private SourceLocation start;

    private SourceLocation end;

    private SourceLocation opStart;

    private SourceLocation opEnd;

    private TypeDetails types;

    private TypeDetails opTypes;

    public ExpressionNode(int uniqueId) {
        super();
        this.uniqueId = Integer.toString(uniqueId);
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        switch(kind) {
            case Name:
                b.append(name);
                break;
            case Function:
                if (function == Function.Item)
                    b.append("[");
                else {
                    b.append(name);
                    b.append("(");
                }
                boolean first = true;
                for (ExpressionNode n : parameters) {
                    if (first)
                        first = false;
                    else
                        b.append(", ");
                    b.append(n.toString());
                }
                if (function == Function.Item)
                    b.append("]");
                else {
                    b.append(")");
                }
                break;
            case Constant:
                b.append(Utilities.escapeJava(constant));
                break;
            case Group:
                b.append("(");
                b.append(group.toString());
                b.append(")");
        }
        if (inner != null) {
            b.append(".");
            b.append(inner.toString());
        }
        if (operation != null) {
            b.append(" ");
            b.append(operation.toCode());
            b.append(" ");
            b.append(opNext.toString());
        }
        return b.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
        if (parameters == null)
            parameters = new ArrayList<ExpressionNode>();
    }

    public boolean isProximal() {
        return proximal;
    }

    public void setProximal(boolean proximal) {
        this.proximal = proximal;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public ExpressionNode getInner() {
        return inner;
    }

    public void setInner(ExpressionNode value) {
        this.inner = value;
    }

    public ExpressionNode getOpNext() {
        return opNext;
    }

    public void setOpNext(ExpressionNode value) {
        this.opNext = value;
    }

    public List<ExpressionNode> getParameters() {
        return parameters;
    }

    public boolean checkName() {
        if (!name.startsWith("$"))
            return true;
        else
            return name.equals("$this");
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public ExpressionNode getGroup() {
        return group;
    }

    public void setGroup(ExpressionNode group) {
        this.group = group;
    }

    public SourceLocation getStart() {
        return start;
    }

    public void setStart(SourceLocation start) {
        this.start = start;
    }

    public SourceLocation getEnd() {
        return end;
    }

    public void setEnd(SourceLocation end) {
        this.end = end;
    }

    public SourceLocation getOpStart() {
        return opStart;
    }

    public void setOpStart(SourceLocation opStart) {
        this.opStart = opStart;
    }

    public SourceLocation getOpEnd() {
        return opEnd;
    }

    public void setOpEnd(SourceLocation opEnd) {
        this.opEnd = opEnd;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public int parameterCount() {
        if (parameters == null)
            return 0;
        else
            return parameters.size();
    }

    public String Canonical() {
        StringBuilder b = new StringBuilder();
        write(b);
        return b.toString();
    }

    public String summary() {
        switch(kind) {
            case Name:
                return uniqueId + ": " + name;
            case Function:
                return uniqueId + ": " + function.toString() + "()";
            case Constant:
                return uniqueId + ": " + constant;
            case Group:
                return uniqueId + ": (Group)";
        }
        return "??";
    }

    private void write(StringBuilder b) {
        switch(kind) {
            case Name:
                b.append(name);
                break;
            case Constant:
                b.append(constant);
                break;
            case Function:
                b.append(function.toCode());
                b.append('(');
                boolean f = true;
                for (ExpressionNode n : parameters) {
                    if (f)
                        f = false;
                    else
                        b.append(", ");
                    n.write(b);
                }
                b.append(')');
                break;
            case Group:
                b.append('(');
                group.write(b);
                b.append(')');
        }
        if (inner != null) {
            b.append('.');
            inner.write(b);
        }
        if (operation != null) {
            b.append(' ');
            b.append(operation.toCode());
            b.append(' ');
            opNext.write(b);
        }
    }

    public String check() {
        switch(kind) {
            case Name:
                if (Utilities.noString(name))
                    return "No Name provided @ " + location();
                break;
            case Function:
                if (function == null)
                    return "No Function id provided @ " + location();
                for (ExpressionNode n : parameters) {
                    String msg = n.check();
                    if (msg != null)
                        return msg;
                }
                break;
            case Constant:
                if (Utilities.noString(constant))
                    return "No Constant provided @ " + location();
                break;
            case Group:
                if (group == null)
                    return "No Group provided @ " + location();
                else {
                    String msg = group.check();
                    if (msg != null)
                        return msg;
                }
        }
        if (inner != null) {
            String msg = inner.check();
            if (msg != null)
                return msg;
        }
        if (operation == null) {
            if (opNext != null)
                return "Next provided when it shouldn't be @ " + location();
        } else {
            if (opNext == null)
                return "No Next provided @ " + location();
            else
                opNext.check();
        }
        return null;
    }

    private String location() {
        return Integer.toString(start.line) + ", " + Integer.toString(start.column);
    }

    public TypeDetails getTypes() {
        return types;
    }

    public void setTypes(TypeDetails types) {
        this.types = types;
    }

    public TypeDetails getOpTypes() {
        return opTypes;
    }

    public void setOpTypes(TypeDetails opTypes) {
        this.opTypes = opTypes;
    }
}