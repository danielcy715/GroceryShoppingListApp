package edu.gatech.seclass.glm;

/**
 * Created by gopalramanujam on 10/10/16.
 */
public class Item implements Comparable<Item> {

    private String name;
    private String type;
    private String unit;
    private double quant;
    private boolean isSelected;
    private boolean isChecked;

    @Override
    public int compareTo(Item anotherItem) {
        return this.getType().compareTo(anotherItem.getType());
    }

    public Item(String name, String type, String unit, double quant, boolean isSelected, boolean isChecked)
    {
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.quant = quant;
        this.isSelected = isSelected;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getQuant(){
        if (unit.equals("Nos")) {
            return Integer.toString((int) quant);
        }
        return Double.toString(quant);
    }

    public void setQuant(double quant){
        this.quant = quant;
    }

    public boolean isSelected(){
        return isSelected;
    }

    public void setSelected(boolean selected){
        this.isSelected = selected;
    }

    public boolean isChecked(){
        return isChecked;
    }

    public void setChecked(boolean checked){
        this.isChecked = checked;
    }


}
