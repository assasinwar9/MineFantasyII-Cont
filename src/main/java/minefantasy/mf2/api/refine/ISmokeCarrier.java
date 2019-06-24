package minefantasy.mf2.api.refine;

public interface ISmokeCarrier {
    int getSmokeValue();

    void setSmokeValue(int smoke);

    int getMaxSmokeStorage();

    boolean canAbsorbIndirect();
}
