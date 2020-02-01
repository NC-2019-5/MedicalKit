package com.netcracker.group5.medkit.model.dto.medicine;

import javax.validation.constraints.NotBlank;

public class SaveMedicineRequestItem {

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

    @NotBlank(message = "Medicine dosage can not be empty")
    private String dosage;

    public SaveMedicineRequestItem() {
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

    public String getDosage(){
        return dosage;
    }

    public void setDosage(String dosage){
        this.dosage = dosage;
    }
}
