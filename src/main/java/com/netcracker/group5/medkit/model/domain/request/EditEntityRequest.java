package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.Requestable;

import java.util.Map;
import java.util.Objects;

public class EditEntityRequest<T extends Requestable> extends Request<T> {

    private Map<String, String> fields;

    private EditEntityRequest() {
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditEntityRequest<?> that = (EditEntityRequest<?>) o;
        return fields.equals(that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fields);
    }

    @Override
    public String toString() {
        return "EditEntityRequest{" +
                "id=" + id +
                ", fields=" + fields +
                ", deliveredTime=" + deliveredTime +
                ", type=" + type +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", body=" + body +
                ", template='" + template + '\'' +
                '}';
    }

    public static <T extends Requestable> EditEntityRequest<T>.Builder newBuilder() {
        return new EditEntityRequest<T>().new Builder();
    }


    public class Builder extends AbstractBuilder<Builder> {

        private Builder() {
        }

        public Builder setFields(Map<String, String> fields) {
            EditEntityRequest.this.fields = fields;
            return this;
        }

        public EditEntityRequest<T> build() {
            return EditEntityRequest.this;
        }
    }
}
