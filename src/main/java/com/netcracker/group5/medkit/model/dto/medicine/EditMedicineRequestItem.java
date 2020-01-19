package com.netcracker.group5.medkit.model.dto.medicine;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EditMedicineRequestItem {

    @NotNull(message = "Medicine id is mandatory")
    @Positive(message = "Medicine id must be greater than 0")
    private Long id;

    @NotBlank(message = "Medicine name can not be empty")
    private String name;

    @NotBlank(message = "Medicine manufacturer can not be empty")
    private String manufacturer;

    @NotBlank(message = "Medicine production form can not be empty")
    private String productionForm;

    @NotBlank(message = "Medicine contraindications can not be empty")
    private String contraindications;

    @NotBlank(message = "Medicine interactions can not be empty")
    private String interactions;

    @NotBlank(message = "Medicine package content can not be empty")
    private String packageContent;

    @NotBlank(message = "Medicine taking method can not be empty")
    private String takingMethod;

    @NotBlank(message = "Medicine description can not be empty")
    private String description;

    public EditMedicineRequestItem() {
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

    public String getInteractions() {
        return interactions;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }

    public String getPackageContent() {
        return packageContent;
    }

    public void setPackageContent(String packageContent) {
        this.packageContent = packageContent;
    }

    public String getTakingMethod() {
        return takingMethod;
    }

    public void setTakingMethod(String takingMethod) {
        this.takingMethod = takingMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
