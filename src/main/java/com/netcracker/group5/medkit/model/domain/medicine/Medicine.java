package com.netcracker.group5.medkit.model.domain.medicine;

import com.netcracker.group5.medkit.model.domain.Requestable;

import java.util.List;
import java.util.Objects;

public class Medicine implements Requestable {

    private Long id;
    private String name;
    private String manufacturer;
    private String productionForm;
    private String contraindications;
    private List<Medicine> interactions;
    private String packageContent;
    private TakingMethod takingMethod;
    private String description;

    private Medicine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProductionForm() {
        return productionForm;
    }

    public void setProductionForm(String productionForm) {
        this.productionForm = productionForm;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public List<Medicine> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<Medicine> interactions) {
        this.interactions = interactions;
    }

    public String getPackageContent() {
        return packageContent;
    }

    public void setPackageContent(String packageContent) {
        this.packageContent = packageContent;
    }

    public TakingMethod getTakingMethod() {
        return takingMethod;
    }

    public void setTakingMethod(TakingMethod takingMethod) {
        this.takingMethod = takingMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return id.equals(medicine.id) &&
                Objects.equals(name, medicine.name) &&
                Objects.equals(manufacturer, medicine.manufacturer) &&
                Objects.equals(productionForm, medicine.productionForm) &&
                Objects.equals(contraindications, medicine.contraindications) &&
                Objects.equals(interactions, medicine.interactions) &&
                Objects.equals(packageContent, medicine.packageContent) &&
                takingMethod == medicine.takingMethod &&
                Objects.equals(description, medicine.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manufacturer, productionForm, contraindications, interactions, packageContent, takingMethod, description);
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", productionForm='" + productionForm + '\'' +
                ", contraindications='" + contraindications + '\'' +
                ", interactions=" + interactions +
                ", packageContent='" + packageContent + '\'' +
                ", takingMethod=" + takingMethod +
                ", description='" + description + '\'' +
                '}';
    }

    public static Builder newBuilder() {
        return new Medicine().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            Medicine.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            Medicine.this.name = name;
            return this;
        }

        public Builder setManufacturer(String manufacturer) {
            Medicine.this.manufacturer = manufacturer;
            return this;
        }

        public Builder setProductionForm(String productionForm) {
            Medicine.this.productionForm = productionForm;
            return this;
        }

        public Builder setContraindications(String contraindications) {
            Medicine.this.contraindications = contraindications;
            return this;
        }

        public Builder setInteractions(List<Medicine> interactions) {
            Medicine.this.interactions = interactions;
            return this;
        }

        public Builder setPackageContent(String packageContent) {
            Medicine.this.packageContent = packageContent;
            return this;
        }

        public Builder setTakingMethod(TakingMethod takingMethod) {
            Medicine.this.takingMethod = takingMethod;
            return this;
        }

        public Builder setDescription(String description) {
            Medicine.this.description = description;
            return this;
        }

        public Medicine build() {
            return Medicine.this;
        }
    }
}
