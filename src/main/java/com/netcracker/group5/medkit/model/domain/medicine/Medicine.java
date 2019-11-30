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

    public Medicine(Long id, String name, String manufacturer, String productionForm, String contraindications, List<Medicine> interactions, String packageContent, TakingMethod takingMethod, String description) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.productionForm = productionForm;
        this.contraindications = contraindications;
        this.interactions = interactions;
        this.packageContent = packageContent;
        this.takingMethod = takingMethod;
        this.description = description;
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
}
