package Mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * MFIIBasilisk - Undefined
 * Created using Tabula 5.1.0
 */
public class MFIIBasilisk extends ModelBase {
    public ModelRenderer Torso;
    public ModelRenderer Backbody;
    public ModelRenderer Neck1;
    public ModelRenderer Shoulder1L;
    public ModelRenderer Shoulder2L;
    public ModelRenderer Shoulder1R;
    public ModelRenderer Shoulder1R_1;
    public ModelRenderer Tail1;
    public ModelRenderer ThighL;
    public ModelRenderer Shoulder1R_2;
    public ModelRenderer Tail2;
    public ModelRenderer Tail3;
    public ModelRenderer Tail4;
    public ModelRenderer ShinL;
    public ModelRenderer FootL;
    public ModelRenderer Toesole12L;
    public ModelRenderer Toesole11L;
    public ModelRenderer Toesole10L;
    public ModelRenderer Toesole9L;
    public ModelRenderer Toetop12L;
    public ModelRenderer Toetop11L;
    public ModelRenderer Toetop10L;
    public ModelRenderer Toetop9L;
    public ModelRenderer Arm1R;
    public ModelRenderer Palm1R;
    public ModelRenderer Toesole12R;
    public ModelRenderer Toesole11R;
    public ModelRenderer Toesole10R;
    public ModelRenderer Toesole9R;
    public ModelRenderer Toetop12R;
    public ModelRenderer Toetop11R;
    public ModelRenderer Toetop10R;
    public ModelRenderer Toetop9R;
    public ModelRenderer Neck2;
    public ModelRenderer Neck3;
    public ModelRenderer Head1;
    public ModelRenderer Head2;
    public ModelRenderer Tooth1;
    public ModelRenderer Tooth2;
    public ModelRenderer Tooth3;
    public ModelRenderer Tooth4;
    public ModelRenderer Headfront;
    public ModelRenderer Upperjaw;
    public ModelRenderer Lowerjaw;
    public ModelRenderer Tooth5;
    public ModelRenderer Tooth6;
    public ModelRenderer Tooth7;
    public ModelRenderer Tooth8;
    public ModelRenderer Lowerjaw2;
    public ModelRenderer Gums;
    public ModelRenderer Lowerjaw3;
    public ModelRenderer Tooth9;
    public ModelRenderer Tooth10;
    public ModelRenderer Tooth11;
    public ModelRenderer Tooth12;
    public ModelRenderer Tooth13;
    public ModelRenderer Tooth14;
    public ModelRenderer Tooth15;
    public ModelRenderer Tooth16;
    public ModelRenderer Tongue1;
    public ModelRenderer Tongue2;
    public ModelRenderer Arm1L;
    public ModelRenderer Palm1L;
    public ModelRenderer Toetop1L;
    public ModelRenderer Toetop2L;
    public ModelRenderer Toetop3L;
    public ModelRenderer Toetop4L;
    public ModelRenderer Toetop1L_1;
    public ModelRenderer Toetop2L_1;
    public ModelRenderer Toetop3L_1;
    public ModelRenderer Toetop4L_1;
    public ModelRenderer Arm2L;
    public ModelRenderer Palm2L;
    public ModelRenderer shape39;
    public ModelRenderer shape39_1;
    public ModelRenderer shape39_2;
    public ModelRenderer shape39_3;
    public ModelRenderer Toetop5L;
    public ModelRenderer Toetop6L;
    public ModelRenderer Toetop7L;
    public ModelRenderer Toetop8L;
    public ModelRenderer Arm1R_1;
    public ModelRenderer Palm1R_1;
    public ModelRenderer Toesole1R;
    public ModelRenderer Toesole9R_1;
    public ModelRenderer Toesole9R_2;
    public ModelRenderer Toesole9R_3;
    public ModelRenderer Toetop1R;
    public ModelRenderer Toetop9R_1;
    public ModelRenderer Toetop9R_2;
    public ModelRenderer Toetop9R_3;
    public ModelRenderer Arm1R_2;
    public ModelRenderer Palm1R_2;
    public ModelRenderer Toesole9R_4;
    public ModelRenderer Toesole9R_5;
    public ModelRenderer Toesole7R;
    public ModelRenderer Toesole8R;
    public ModelRenderer Toetop9R_4;
    public ModelRenderer Toetop9R_5;
    public ModelRenderer Toetop7R;
    public ModelRenderer Toetop8R;

    public MFIIBasilisk() {
        this.textureWidth = 512;
        this.textureHeight = 256;
        this.Toesole12R = new ModelRenderer(this, 205, 185);
        this.Toesole12R.setRotationPoint(-0.5F, -1.0F, -3.0F);
        this.Toesole12R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole12R, -1.9577358219620393F, 1.6390387005478748F, 0.0F);
        this.Toesole12L = new ModelRenderer(this, 195, 185);
        this.Toesole12L.setRotationPoint(0.5F, -1.0F, -3.0F);
        this.Toesole12L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole12L, 1.9577358219620393F, 1.593485607070823F, 0.0F);
        this.Toetop2L_1 = new ModelRenderer(this, 195, 185);
        this.Toetop2L_1.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop2L_1.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop2L_1, 0.0F, -0.22759093446006054F, 0.0F);
        this.Shoulder1R = new ModelRenderer(this, 170, 230);
        this.Shoulder1R.mirror = true;
        this.Shoulder1R.setRotationPoint(-7.0F, 9.0F, 1.0F);
        this.Shoulder1R.addBox(-7.0F, 0.0F, 0.0F, 7, 5, 6, 0.0F);
        this.setRotateAngle(Shoulder1R, 0.31869712141416456F, 0.4553564018453205F, -0.5918411493512771F);
        this.shape39_3 = new ModelRenderer(this, 195, 185);
        this.shape39_3.setRotationPoint(2.5F, 0.5F, -3.0F);
        this.shape39_3.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(shape39_3, -1.0016444577195458F, 1.593485607070823F, 0.0F);
        this.Shoulder1R_1 = new ModelRenderer(this, 205, 230);
        this.Shoulder1R_1.mirror = true;
        this.Shoulder1R_1.setRotationPoint(-7.0F, 9.3F, 13.0F);
        this.Shoulder1R_1.addBox(-8.0F, 0.0F, 0.0F, 8, 5, 6, 0.0F);
        this.setRotateAngle(Shoulder1R_1, 0.22759093446006054F, 0.22759093446006054F, -0.5462880558742251F);
        this.ShinL = new ModelRenderer(this, 240, 210);
        this.ShinL.setRotationPoint(8.0F, 0.3F, 0.0F);
        this.ShinL.addBox(0.0F, 0.0F, -11.0F, 5, 4, 11, 0.0F);
        this.setRotateAngle(ShinL, 0.045553093477052F, -2.4586453172844123F, 0.0F);
        this.Toesole9R_4 = new ModelRenderer(this, 205, 185);
        this.Toesole9R_4.setRotationPoint(-0.5F, 3.0F, -3.0F);
        this.Toesole9R_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole9R_4, 0.36425021489121656F, 1.593485607070823F, 0.0F);
        this.Toetop9R_3 = new ModelRenderer(this, 205, 185);
        this.Toetop9R_3.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop9R_3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop9R_3, 0.0F, 0.22759093446006054F, 0.0F);
        this.Toetop12R = new ModelRenderer(this, 205, 185);
        this.Toetop12R.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop12R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop12R, 0.0F, 0.22759093446006054F, 0.0F);
        this.Tooth15 = new ModelRenderer(this, 135, 160);
        this.Tooth15.setRotationPoint(-2.0F, -1.0F, -4.9F);
        this.Tooth15.addBox(0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth15, 0.0F, 0.0F, -0.18203784098300857F);
        this.Toetop7L = new ModelRenderer(this, 195, 185);
        this.Toetop7L.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop7L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop7L, 0.0F, -0.22759093446006054F, 0.0F);
        this.Tooth14 = new ModelRenderer(this, 135, 160);
        this.Tooth14.setRotationPoint(2.0F, -1.0F, -3.0F);
        this.Tooth14.addBox(-1.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth14, 0.091106186954104F, -0.045553093477052F, 0.18203784098300857F);
        this.Toetop9R_2 = new ModelRenderer(this, 205, 185);
        this.Toetop9R_2.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop9R_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop9R_2, 0.0F, 0.22759093446006054F, 0.0F);
        this.Toetop3L_1 = new ModelRenderer(this, 195, 185);
        this.Toetop3L_1.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop3L_1.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop3L_1, 0.0F, -0.22759093446006054F, 0.0F);
        this.Toetop9R = new ModelRenderer(this, 205, 185);
        this.Toetop9R.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop9R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop9R, 0.0F, 0.22759093446006054F, 0.0F);
        this.Tooth9 = new ModelRenderer(this, 135, 160);
        this.Tooth9.setRotationPoint(-2.5F, 1.0F, -2.3F);
        this.Tooth9.addBox(0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth9, 0.091106186954104F, 0.045553093477052F, -0.18203784098300857F);
        this.Shoulder1R_2 = new ModelRenderer(this, 240, 230);
        this.Shoulder1R_2.setRotationPoint(-3.5F, 6.5F, 12.0F);
        this.Shoulder1R_2.addBox(-8.0F, 0.0F, 0.0F, 8, 5, 7, 0.0F);
        this.setRotateAngle(Shoulder1R_2, -0.27314402793711257F, -0.5009094953223726F, -0.5197590512439113F);
        this.Palm1R_2 = new ModelRenderer(this, 205, 200);
        this.Palm1R_2.setRotationPoint(4.99F, 0.0F, -10.0F);
        this.Palm1R_2.addBox(-5.0F, 0.0F, -4.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(Palm1R_2, 0.8651597102135892F, 0.0F, 0.0F);
        this.Lowerjaw = new ModelRenderer(this, 100, 180);
        this.Lowerjaw.setRotationPoint(0.0F, 6.5F, 5.2F);
        this.Lowerjaw.addBox(-3.5F, 0.0F, -3.0F, 7, 3, 5, 0.0F);
        this.setRotateAngle(Lowerjaw, -0.18203784098300857F, 0.0F, 0.0F);
        this.Tongue2 = new ModelRenderer(this, 135, 150);
        this.Tongue2.setRotationPoint(0.0F, 0.0F, -3.5F);
        this.Tongue2.addBox(-1.0F, 0.0F, -5.0F, 2, 1, 5, 0.0F);
        this.Toetop2L = new ModelRenderer(this, 195, 185);
        this.Toetop2L.setRotationPoint(0.6F, 1.5F, -3.0F);
        this.Toetop2L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop2L, -0.5009094953223726F, 1.593485607070823F, 0.0F);
        this.Tooth10 = new ModelRenderer(this, 135, 160);
        this.Tooth10.setRotationPoint(2.5F, 1.0F, -2.3F);
        this.Tooth10.addBox(-1.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth10, 0.091106186954104F, 0.045553093477052F, 0.18203784098300857F);
        this.Palm1R_1 = new ModelRenderer(this, 170, 200);
        this.Palm1R_1.setRotationPoint(4.99F, 0.0F, -10.0F);
        this.Palm1R_1.addBox(-5.0F, 0.0F, -4.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(Palm1R_1, 0.7285004297824331F, 0.0F, 0.0F);
        this.Toetop1L_1 = new ModelRenderer(this, 195, 185);
        this.Toetop1L_1.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop1L_1.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop1L_1, 0.0F, -0.22759093446006054F, 0.0F);
        this.Arm1R = new ModelRenderer(this, 240, 210);
        this.Arm1R.mirror = true;
        this.Arm1R.setRotationPoint(-8.0F, 0.3F, 0.0F);
        this.Arm1R.addBox(-5.0F, 0.0F, -11.0F, 5, 4, 11, 0.0F);
        this.setRotateAngle(Arm1R, 0.045553093477052F, 2.4586453172844123F, 0.0F);
        this.Tooth8 = new ModelRenderer(this, 135, 160);
        this.Tooth8.setRotationPoint(2.0F, 1.0F, 1.0F);
        this.Tooth8.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth8, -0.091106186954104F, 0.0F, -0.18203784098300857F);
        this.ThighL = new ModelRenderer(this, 240, 230);
        this.ThighL.setRotationPoint(3.5F, 6.5F, 12.0F);
        this.ThighL.addBox(0.0F, 0.0F, 0.0F, 8, 5, 7, 0.0F);
        this.setRotateAngle(ThighL, -0.27314402793711257F, 0.5009094953223726F, 0.5197590512439113F);
        this.Toetop3L = new ModelRenderer(this, 195, 185);
        this.Toetop3L.setRotationPoint(1.5F, 0.5F, -3.0F);
        this.Toetop3L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop3L, -0.6373942428283291F, 1.593485607070823F, 0.0F);
        this.Upperjaw = new ModelRenderer(this, 100, 170);
        this.Upperjaw.setRotationPoint(0.0F, 2.45F, -5.6F);
        this.Upperjaw.addBox(-2.5F, 0.0F, 0.0F, 5, 2, 6, 0.0F);
        this.setRotateAngle(Upperjaw, -0.22759093446006054F, 0.0F, 0.0F);
        this.Toesole10L = new ModelRenderer(this, 195, 185);
        this.Toesole10L.setRotationPoint(3.5F, -0.8F, -3.0F);
        this.Toesole10L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole10L, 2.504198410761464F, 1.593485607070823F, 0.0F);
        this.Toetop9L = new ModelRenderer(this, 195, 185);
        this.Toetop9L.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop9L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop9L, 0.0F, -0.22759093446006054F, 0.0F);
        this.Palm2L = new ModelRenderer(this, 205, 200);
        this.Palm2L.setRotationPoint(-4.99F, 0.0F, -10.0F);
        this.Palm2L.addBox(0.0F, 0.0F, -4.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(Palm2L, 0.8651597102135892F, 0.0F, 0.0F);
        this.shape39_1 = new ModelRenderer(this, 195, 185);
        this.shape39_1.setRotationPoint(0.6F, 1.5F, -3.0F);
        this.shape39_1.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(shape39_1, -0.5009094953223726F, 1.593485607070823F, 0.0F);
        this.Toetop8R = new ModelRenderer(this, 205, 185);
        this.Toetop8R.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop8R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop8R, 0.0F, 0.22759093446006054F, 0.0F);
        this.Toetop9R_4 = new ModelRenderer(this, 205, 185);
        this.Toetop9R_4.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop9R_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop9R_4, 0.0F, 0.22759093446006054F, 0.0F);
        this.Lowerjaw3 = new ModelRenderer(this, 135, 170);
        this.Lowerjaw3.setRotationPoint(0.0F, 2.0F, -3.7F);
        this.Lowerjaw3.addBox(-2.5F, -2.0F, -5.0F, 5, 2, 5, 0.0F);
        this.setRotateAngle(Lowerjaw3, -0.091106186954104F, 0.0F, 0.0F);
        this.Toesole9R_2 = new ModelRenderer(this, 205, 185);
        this.Toesole9R_2.setRotationPoint(-1.5F, 0.5F, -3.0F);
        this.Toesole9R_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole9R_2, 0.6373942428283291F, 1.593485607070823F, 0.0F);
        this.Lowerjaw2 = new ModelRenderer(this, 135, 180);
        this.Lowerjaw2.setRotationPoint(0.0F, 0.5F, -2.8F);
        this.Lowerjaw2.addBox(-3.0F, 0.0F, -4.0F, 6, 2, 4, 0.0F);
        this.setRotateAngle(Lowerjaw2, 0.045553093477052F, 0.0F, 0.0F);
        this.Toetop1L = new ModelRenderer(this, 195, 185);
        this.Toetop1L.setRotationPoint(0.5F, 3.0F, -3.0F);
        this.Toetop1L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop1L, -0.36425021489121656F, 1.593485607070823F, 0.0F);
        this.Toetop5L = new ModelRenderer(this, 195, 185);
        this.Toetop5L.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop5L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop5L, 0.0F, -0.22759093446006054F, 0.0F);
        this.Toesole9R_5 = new ModelRenderer(this, 205, 185);
        this.Toesole9R_5.setRotationPoint(-0.5F, 1.5F, -3.0F);
        this.Toesole9R_5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole9R_5, 0.5009094953223726F, 1.593485607070823F, 0.0F);
        this.Neck1 = new ModelRenderer(this, 100, 230);
        this.Neck1.setRotationPoint(0.0F, 0.9F, 2.0F);
        this.Neck1.addBox(-5.0F, 0.0F, -6.0F, 10, 12, 6, 0.0F);
        this.Tooth6 = new ModelRenderer(this, 135, 160);
        this.Tooth6.setRotationPoint(2.0F, 1.0F, 3.3F);
        this.Tooth6.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth6, -0.045553093477052F, 0.0F, -0.18203784098300857F);
        this.Tooth11 = new ModelRenderer(this, 135, 160);
        this.Tooth11.setRotationPoint(-2.1F, 1.0F, -4.3F);
        this.Tooth11.addBox(0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth11, 0.136659280431156F, -0.091106186954104F, -0.18203784098300857F);
        this.Tooth4 = new ModelRenderer(this, 135, 160);
        this.Tooth4.setRotationPoint(2.5F, 5.0F, -3.5F);
        this.Tooth4.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth4, -0.18203784098300857F, 0.0F, -0.18203784098300857F);
        this.Tooth7 = new ModelRenderer(this, 135, 160);
        this.Tooth7.setRotationPoint(-2.0F, 1.0F, 1.2F);
        this.Tooth7.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth7, -0.091106186954104F, 0.0F, 0.18203784098300857F);
        this.Tooth5 = new ModelRenderer(this, 135, 160);
        this.Tooth5.setRotationPoint(-2.0F, 1.0F, 3.3F);
        this.Tooth5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth5, -0.045553093477052F, 0.0F, 0.18203784098300857F);
        this.Toetop4L_1 = new ModelRenderer(this, 195, 185);
        this.Toetop4L_1.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop4L_1.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop4L_1, 0.0F, -0.22759093446006054F, 0.0F);
        this.Toetop6L = new ModelRenderer(this, 195, 185);
        this.Toetop6L.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop6L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop6L, 0.0F, -0.22759093446006054F, 0.0F);
        this.Toetop9R_5 = new ModelRenderer(this, 205, 185);
        this.Toetop9R_5.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop9R_5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop9R_5, 0.0F, 0.22759093446006054F, 0.0F);
        this.Toesole11R = new ModelRenderer(this, 205, 185);
        this.Toesole11R.setRotationPoint(-2.5F, -0.5F, -3.0F);
        this.Toesole11R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole11R, -2.1399481958702475F, 1.593485607070823F, 0.0F);
        this.Toetop10R = new ModelRenderer(this, 205, 185);
        this.Toetop10R.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop10R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop10R, 0.0F, 0.22759093446006054F, 0.0F);
        this.Toesole9R_3 = new ModelRenderer(this, 205, 185);
        this.Toesole9R_3.setRotationPoint(-2.5F, 0.5F, -3.0F);
        this.Toesole9R_3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole9R_3, 1.0016444577195458F, 1.593485607070823F, 0.0F);
        this.Tail4 = new ModelRenderer(this, 5, 75);
        this.Tail4.setRotationPoint(0.0F, 1.0F, 12.0F);
        this.Tail4.addBox(-2.5F, 0.0F, 0.0F, 5, 6, 13, 0.0F);
        this.setRotateAngle(Tail4, 0.045553093477052F, 0.0F, 0.0F);
        this.Gums = new ModelRenderer(this, 100, 135);
        this.Gums.setRotationPoint(0.0F, -2.5F, 0.2F);
        this.Gums.addBox(-2.5F, 0.0F, -3.0F, 5, 5, 3, 0.0F);
        this.setRotateAngle(Gums, -0.091106186954104F, 0.0F, 0.0F);
        this.Toesole9R_1 = new ModelRenderer(this, 205, 185);
        this.Toesole9R_1.setRotationPoint(-0.5F, 1.5F, -3.0F);
        this.Toesole9R_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole9R_1, 0.5009094953223726F, 1.593485607070823F, 0.0F);
        this.Shoulder1L = new ModelRenderer(this, 170, 230);
        this.Shoulder1L.setRotationPoint(7.0F, 9.0F, 1.0F);
        this.Shoulder1L.addBox(0.0F, 0.0F, 0.0F, 7, 5, 6, 0.0F);
        this.setRotateAngle(Shoulder1L, 0.31869712141416456F, -0.4553564018453205F, 0.5918411493512771F);
        this.FootL = new ModelRenderer(this, 240, 200);
        this.FootL.setRotationPoint(5.01F, 0.0F, -11.0F);
        this.FootL.addBox(0.0F, -4.0F, -4.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(FootL, -0.8196066167365371F, 0.0F, 3.141592653589793F);
        this.Headfront = new ModelRenderer(this, 100, 150);
        this.Headfront.setRotationPoint(-0.01F, 0.0F, -4.0F);
        this.Headfront.addBox(-2.5F, 0.0F, -3.0F, 5, 3, 3, 0.0F);
        this.setRotateAngle(Headfront, 1.0016444577195458F, 0.0F, 0.0F);
        this.Toesole9L = new ModelRenderer(this, 195, 185);
        this.Toesole9L.setRotationPoint(3.5F, -2.5F, -3.0F);
        this.Toesole9L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole9L, 2.7317893452215247F, 1.593485607070823F, 0.0F);
        this.Head1 = new ModelRenderer(this, 135, 190);
        this.Head1.setRotationPoint(0.0F, 0.0F, -5.0F);
        this.Head1.addBox(-3.0F, 0.0F, -4.0F, 6, 6, 4, 0.0F);
        this.setRotateAngle(Head1, 0.045553093477052F, 0.0F, 0.0F);
        this.Head2 = new ModelRenderer(this, 100, 160);
        this.Head2.setRotationPoint(-0.01F, 0.3F, -4.0F);
        this.Head2.addBox(-2.5F, 0.0F, -4.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(Head2, 0.22759093446006054F, 0.0F, 0.0F);
        this.Tail1 = new ModelRenderer(this, 5, 150);
        this.Tail1.setRotationPoint(0.0F, 1.0F, 18.0F);
        this.Tail1.addBox(-5.5F, 0.0F, 0.0F, 11, 12, 11, 0.0F);
        this.setRotateAngle(Tail1, -0.091106186954104F, 0.0F, 0.0F);
        this.Tooth1 = new ModelRenderer(this, 135, 160);
        this.Tooth1.setRotationPoint(-2.5F, 5.0F, -1.8F);
        this.Tooth1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth1, 0.091106186954104F, 0.0F, 0.18203784098300857F);
        this.Toetop4L = new ModelRenderer(this, 195, 185);
        this.Toetop4L.setRotationPoint(2.5F, 0.5F, -3.0F);
        this.Toetop4L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop4L, -1.0016444577195458F, 1.593485607070823F, 0.0F);
        this.Tooth3 = new ModelRenderer(this, 135, 160);
        this.Tooth3.setRotationPoint(-2.5F, 5.0F, -3.5F);
        this.Tooth3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth3, -0.091106186954104F, 0.0F, 0.18203784098300857F);
        this.Toesole7R = new ModelRenderer(this, 205, 185);
        this.Toesole7R.setRotationPoint(-1.5F, 0.5F, -3.0F);
        this.Toesole7R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole7R, 0.6373942428283291F, 1.593485607070823F, 0.0F);
        this.Arm2L = new ModelRenderer(this, 205, 210);
        this.Arm2L.setRotationPoint(8.0F, 0.3F, 6.0F);
        this.Arm2L.addBox(-5.0F, 0.0F, -10.0F, 5, 4, 10, 0.0F);
        this.setRotateAngle(Arm2L, 0.045553093477052F, -0.9105382707654417F, 0.0F);
        this.Toesole8R = new ModelRenderer(this, 205, 185);
        this.Toesole8R.setRotationPoint(-2.5F, 0.5F, -3.0F);
        this.Toesole8R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole8R, 1.0016444577195458F, 1.593485607070823F, 0.0F);
        this.Toetop7R = new ModelRenderer(this, 205, 185);
        this.Toetop7R.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop7R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop7R, 0.0F, 0.22759093446006054F, 0.0F);
        this.Shoulder2L = new ModelRenderer(this, 205, 230);
        this.Shoulder2L.setRotationPoint(7.0F, 9.3F, 13.0F);
        this.Shoulder2L.addBox(0.0F, 0.0F, 0.0F, 8, 5, 6, 0.0F);
        this.setRotateAngle(Shoulder2L, 0.22759093446006054F, -0.22759093446006054F, 0.5462880558742251F);
        this.Tooth2 = new ModelRenderer(this, 135, 160);
        this.Tooth2.setRotationPoint(2.5F, 5.0F, -1.8F);
        this.Tooth2.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth2, 0.091106186954104F, 0.0F, -0.18203784098300857F);
        this.Toetop12L = new ModelRenderer(this, 195, 185);
        this.Toetop12L.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop12L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop12L, 0.0F, -0.22759093446006054F, 0.0F);
        this.Tail2 = new ModelRenderer(this, 5, 125);
        this.Tail2.setRotationPoint(0.0F, 1.0F, 9.0F);
        this.Tail2.addBox(-4.5F, 0.0F, 0.0F, 9, 10, 12, 0.0F);
        this.setRotateAngle(Tail2, 0.045553093477052F, 0.0F, 0.0F);
        this.Toesole9R = new ModelRenderer(this, 205, 185);
        this.Toesole9R.setRotationPoint(-3.5F, -2.5F, -3.0F);
        this.Toesole9R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole9R, -2.7317893452215247F, 1.593485607070823F, 0.0F);
        this.Arm1R_2 = new ModelRenderer(this, 205, 210);
        this.Arm1R_2.mirror = true;
        this.Arm1R_2.setRotationPoint(-8.0F, 0.3F, 6.0F);
        this.Arm1R_2.addBox(0.0F, 0.0F, -10.0F, 5, 4, 10, 0.0F);
        this.setRotateAngle(Arm1R_2, 0.045553093477052F, 0.9105382707654417F, 0.0F);
        this.Toesole10R = new ModelRenderer(this, 205, 185);
        this.Toesole10R.setRotationPoint(-3.5F, -0.8F, -3.0F);
        this.Toesole10R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole10R, -2.5953045977155678F, 1.593485607070823F, 0.0F);
        this.Toetop11L = new ModelRenderer(this, 195, 185);
        this.Toetop11L.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop11L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop11L, 0.0F, -0.22759093446006054F, 0.0F);
        this.Toesole11L = new ModelRenderer(this, 195, 185);
        this.Toesole11L.setRotationPoint(2.5F, -0.5F, -3.0F);
        this.Toesole11L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole11L, 2.1399481958702475F, 1.593485607070823F, 0.0F);
        this.Tooth16 = new ModelRenderer(this, 135, 160);
        this.Tooth16.setRotationPoint(2.0F, -1.0F, -4.9F);
        this.Tooth16.addBox(-1.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth16, 0.0F, 0.0F, 0.18203784098300857F);
        this.Neck2 = new ModelRenderer(this, 100, 210);
        this.Neck2.setRotationPoint(0.0F, 0.5F, -5.0F);
        this.Neck2.addBox(-4.0F, 0.0F, -5.0F, 8, 10, 5, 0.0F);
        this.Tooth13 = new ModelRenderer(this, 135, 160);
        this.Tooth13.setRotationPoint(-2.0F, -1.0F, -3.0F);
        this.Tooth13.addBox(0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth13, 0.091106186954104F, -0.045553093477052F, -0.18203784098300857F);
        this.Backbody = new ModelRenderer(this, 5, 180);
        this.Backbody.setRotationPoint(0.0F, 0.0F, 17.0F);
        this.Backbody.addBox(-6.5F, 0.0F, 0.0F, 13, 15, 18, 0.0F);
        this.setRotateAngle(Backbody, -0.136659280431156F, 0.0F, 0.0F);
        this.Toetop11R = new ModelRenderer(this, 205, 185);
        this.Toetop11R.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop11R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop11R, 0.0F, 0.22759093446006054F, 0.0F);
        this.Toetop9R_1 = new ModelRenderer(this, 205, 185);
        this.Toetop9R_1.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop9R_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop9R_1, 0.0F, 0.22759093446006054F, 0.0F);
        this.Neck3 = new ModelRenderer(this, 100, 190);
        this.Neck3.setRotationPoint(0.0F, 0.5F, -3.0F);
        this.Neck3.addBox(-3.5F, 0.0F, -5.0F, 7, 6, 5, 0.0F);
        this.setRotateAngle(Neck3, 0.091106186954104F, 0.0F, 0.0F);
        this.Toetop1R = new ModelRenderer(this, 205, 185);
        this.Toetop1R.setRotationPoint(-1.0F, 0.0F, 0.2F);
        this.Toetop1R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop1R, 0.0F, 0.22759093446006054F, 0.0F);
        this.Torso = new ModelRenderer(this, 5, 220);
        this.Torso.setRotationPoint(0.0F, 3.0F, -14.0F);
        this.Torso.addBox(-7.0F, 0.0F, 0.0F, 14, 15, 17, 0.0F);
        this.setRotateAngle(Torso, 0.091106186954104F, 0.0F, 0.0F);
        this.shape39_2 = new ModelRenderer(this, 195, 185);
        this.shape39_2.setRotationPoint(1.5F, 0.5F, -3.0F);
        this.shape39_2.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(shape39_2, -0.6373942428283291F, 1.593485607070823F, 0.0F);
        this.Tail3 = new ModelRenderer(this, 5, 100);
        this.Tail3.setRotationPoint(0.0F, 1.0F, 11.0F);
        this.Tail3.addBox(-3.5F, 0.0F, 0.0F, 7, 8, 13, 0.0F);
        this.setRotateAngle(Tail3, 0.045553093477052F, 0.0F, 0.0F);
        this.Tooth12 = new ModelRenderer(this, 135, 160);
        this.Tooth12.setRotationPoint(2.1F, 1.0F, -4.3F);
        this.Tooth12.addBox(-1.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(Tooth12, 0.136659280431156F, 0.091106186954104F, 0.18203784098300857F);
        this.Arm1L = new ModelRenderer(this, 170, 210);
        this.Arm1L.setRotationPoint(7.0F, 0.3F, 6.0F);
        this.Arm1L.addBox(-5.0F, 0.0F, -10.0F, 5, 4, 10, 0.0F);
        this.setRotateAngle(Arm1L, 0.045553093477052F, -0.7285004297824331F, 0.0F);
        this.Arm1R_1 = new ModelRenderer(this, 170, 210);
        this.Arm1R_1.mirror = true;
        this.Arm1R_1.setRotationPoint(-7.0F, 0.3F, 6.0F);
        this.Arm1R_1.addBox(0.0F, 0.0F, -10.0F, 5, 4, 10, 0.0F);
        this.setRotateAngle(Arm1R_1, 0.045553093477052F, 0.7285004297824331F, 0.0F);
        this.Toesole1R = new ModelRenderer(this, 205, 185);
        this.Toesole1R.setRotationPoint(-0.5F, 3.0F, -3.0F);
        this.Toesole1R.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toesole1R, 0.36425021489121656F, 1.593485607070823F, 0.0F);
        this.shape39 = new ModelRenderer(this, 195, 185);
        this.shape39.setRotationPoint(0.5F, 3.0F, -3.0F);
        this.shape39.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(shape39, -0.36425021489121656F, 1.593485607070823F, 0.0F);
        this.Toetop8L = new ModelRenderer(this, 195, 185);
        this.Toetop8L.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop8L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop8L, 0.0F, -0.22759093446006054F, 0.0F);
        this.Palm1R = new ModelRenderer(this, 240, 200);
        this.Palm1R.setRotationPoint(-5.01F, 0.0F, -11.0F);
        this.Palm1R.addBox(-5.0F, -4.0F, -4.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(Palm1R, -0.8196066167365371F, 0.0F, 3.141592653589793F);
        this.Tongue1 = new ModelRenderer(this, 135, 140);
        this.Tongue1.setRotationPoint(0.0F, 2.0F, -2.0F);
        this.Tongue1.addBox(-1.5F, 0.0F, -4.0F, 3, 1, 4, 0.0F);
        this.setRotateAngle(Tongue1, 0.136659280431156F, 0.0F, 0.0F);
        this.Palm1L = new ModelRenderer(this, 170, 200);
        this.Palm1L.setRotationPoint(-4.99F, 0.0F, -10.0F);
        this.Palm1L.addBox(0.0F, 0.0F, -4.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(Palm1L, 0.7285004297824331F, 0.0F, 0.0F);
        this.Toetop10L = new ModelRenderer(this, 195, 185);
        this.Toetop10L.setRotationPoint(-1.0F, 0.0F, -0.2F);
        this.Toetop10L.addBox(0.0F, 0.0F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(Toetop10L, 0.0F, -0.22759093446006054F, 0.0F);
        this.Palm1R.addChild(this.Toesole12R);
        this.FootL.addChild(this.Toesole12L);
        this.Toetop2L.addChild(this.Toetop2L_1);
        this.Torso.addChild(this.Shoulder1R);
        this.Palm2L.addChild(this.shape39_3);
        this.Torso.addChild(this.Shoulder1R_1);
        this.ThighL.addChild(this.ShinL);
        this.Palm1R_2.addChild(this.Toesole9R_4);
        this.Toesole9R_3.addChild(this.Toetop9R_3);
        this.Toesole12R.addChild(this.Toetop12R);
        this.Lowerjaw3.addChild(this.Tooth15);
        this.shape39_2.addChild(this.Toetop7L);
        this.Lowerjaw3.addChild(this.Tooth14);
        this.Toesole9R_2.addChild(this.Toetop9R_2);
        this.Toetop3L.addChild(this.Toetop3L_1);
        this.Toesole9R.addChild(this.Toetop9R);
        this.Lowerjaw2.addChild(this.Tooth9);
        this.Backbody.addChild(this.Shoulder1R_2);
        this.Arm1R_2.addChild(this.Palm1R_2);
        this.Head2.addChild(this.Lowerjaw);
        this.Tongue1.addChild(this.Tongue2);
        this.Palm1L.addChild(this.Toetop2L);
        this.Lowerjaw2.addChild(this.Tooth10);
        this.Arm1R_1.addChild(this.Palm1R_1);
        this.Toetop1L.addChild(this.Toetop1L_1);
        this.Shoulder1R_2.addChild(this.Arm1R);
        this.Upperjaw.addChild(this.Tooth8);
        this.Backbody.addChild(this.ThighL);
        this.Palm1L.addChild(this.Toetop3L);
        this.Head2.addChild(this.Upperjaw);
        this.FootL.addChild(this.Toesole10L);
        this.Toesole9L.addChild(this.Toetop9L);
        this.Arm2L.addChild(this.Palm2L);
        this.Palm2L.addChild(this.shape39_1);
        this.Toesole8R.addChild(this.Toetop8R);
        this.Toesole9R_4.addChild(this.Toetop9R_4);
        this.Lowerjaw2.addChild(this.Lowerjaw3);
        this.Palm1R_1.addChild(this.Toesole9R_2);
        this.Lowerjaw.addChild(this.Lowerjaw2);
        this.Palm1L.addChild(this.Toetop1L);
        this.shape39.addChild(this.Toetop5L);
        this.Palm1R_2.addChild(this.Toesole9R_5);
        this.Torso.addChild(this.Neck1);
        this.Upperjaw.addChild(this.Tooth6);
        this.Lowerjaw2.addChild(this.Tooth11);
        this.Head1.addChild(this.Tooth4);
        this.Upperjaw.addChild(this.Tooth7);
        this.Upperjaw.addChild(this.Tooth5);
        this.Toetop4L.addChild(this.Toetop4L_1);
        this.shape39_1.addChild(this.Toetop6L);
        this.Toesole9R_5.addChild(this.Toetop9R_5);
        this.Palm1R.addChild(this.Toesole11R);
        this.Toesole10R.addChild(this.Toetop10R);
        this.Palm1R_1.addChild(this.Toesole9R_3);
        this.Tail3.addChild(this.Tail4);
        this.Lowerjaw.addChild(this.Gums);
        this.Palm1R_1.addChild(this.Toesole9R_1);
        this.Torso.addChild(this.Shoulder1L);
        this.ShinL.addChild(this.FootL);
        this.Head2.addChild(this.Headfront);
        this.FootL.addChild(this.Toesole9L);
        this.Neck3.addChild(this.Head1);
        this.Head1.addChild(this.Head2);
        this.Backbody.addChild(this.Tail1);
        this.Head1.addChild(this.Tooth1);
        this.Palm1L.addChild(this.Toetop4L);
        this.Head1.addChild(this.Tooth3);
        this.Palm1R_2.addChild(this.Toesole7R);
        this.Shoulder2L.addChild(this.Arm2L);
        this.Palm1R_2.addChild(this.Toesole8R);
        this.Toesole7R.addChild(this.Toetop7R);
        this.Torso.addChild(this.Shoulder2L);
        this.Head1.addChild(this.Tooth2);
        this.Toesole12L.addChild(this.Toetop12L);
        this.Tail1.addChild(this.Tail2);
        this.Palm1R.addChild(this.Toesole9R);
        this.Shoulder1R_1.addChild(this.Arm1R_2);
        this.Palm1R.addChild(this.Toesole10R);
        this.Toesole11L.addChild(this.Toetop11L);
        this.FootL.addChild(this.Toesole11L);
        this.Lowerjaw3.addChild(this.Tooth16);
        this.Neck1.addChild(this.Neck2);
        this.Lowerjaw3.addChild(this.Tooth13);
        this.Torso.addChild(this.Backbody);
        this.Toesole11R.addChild(this.Toetop11R);
        this.Toesole9R_1.addChild(this.Toetop9R_1);
        this.Neck2.addChild(this.Neck3);
        this.Toesole1R.addChild(this.Toetop1R);
        this.Palm2L.addChild(this.shape39_2);
        this.Tail2.addChild(this.Tail3);
        this.Lowerjaw2.addChild(this.Tooth12);
        this.Shoulder1L.addChild(this.Arm1L);
        this.Shoulder1R.addChild(this.Arm1R_1);
        this.Palm1R_1.addChild(this.Toesole1R);
        this.Palm2L.addChild(this.shape39);
        this.shape39_3.addChild(this.Toetop8L);
        this.Arm1R.addChild(this.Palm1R);
        this.Gums.addChild(this.Tongue1);
        this.Arm1L.addChild(this.Palm1L);
        this.Toesole10L.addChild(this.Toetop10L);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Torso.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
