package com.kh.middle.bean.Car;

public class car {

    String Model;           // 모델명
    String Company;         // 제조회사
    String Oil;             // 기름 종류
    int Displacement;    // 배기량
    int FuelEfficiency;   // 연비

    public car(String model, String company, String oil, int displacement, int fuelEfficiency) {
        Model = model;
        Company = company;
        Oil = oil;
        Displacement = displacement;
        FuelEfficiency = fuelEfficiency;
    }

    @Override
    public String toString() {
        return "car{" +
                "Model='" + Model + '\'' +
                ", Company='" + Company + '\'' +
                ", Oil='" + Oil + '\'' +
                ", Displacement=" + Displacement +
                ", FuelEfficiency=" + FuelEfficiency +
                '}';
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getOil() {
        return Oil;
    }

    public void setOil(String oil) {
        Oil = oil;
    }

    public int getDisplacement() {
        return Displacement;
    }

    public void setDisplacement(int displacement) {
        Displacement = displacement;
    }

    public int getFuelEfficiency() {
        return FuelEfficiency;
    }

    public void setFuelEfficiency(int fuelEfficiency) {
        FuelEfficiency = fuelEfficiency;
    }
}
