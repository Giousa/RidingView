package com.giou.ridingview.objparser;


/**
 * Description:
 * Author:Giousa
 * Date:2016/12/19
 * Email:65489469@qq.com
 */
public interface BuilderInterface {

    int NO_SMOOTHING_GROUP = 0;
    int EMPTY_VERTEX_VALUE = Integer.MIN_VALUE;
    int MTL_KA = 0;
    int MTL_KD = 1;
    int MTL_KS = 2;
    int MTL_TF = 3;
    int MTL_MAP_KA = 0;
    int MTL_MAP_KD = 1;
    int MTL_MAP_KS = 2;
    int MTL_MAP_NS = 3;
    int MTL_MAP_D = 4;
    int MTL_DECAL = 5;
    int MTL_DISP = 6;
    int MTL_BUMP = 7;
    int MTL_REFL_TYPE_UNKNOWN = -1;
    int MTL_REFL_TYPE_SPHERE = 0;
    int MTL_REFL_TYPE_CUBE_TOP = 1;
    int MTL_REFL_TYPE_CUBE_BOTTOM = 2;
    int MTL_REFL_TYPE_CUBE_FRONT = 3;
    int MTL_REFL_TYPE_CUBE_BACK = 4;
    int MTL_REFL_TYPE_CUBE_LEFT = 5;
    int MTL_REFL_TYPE_CUBE_RIGHT = 6;

    void setObjFilename(String filename);

    void addVertexGeometric(float x, float y, float z);

    void addVertexTexture(float u, float v);

    void addVertexNormal(float x, float y, float z);

    void addPoints(int values[]);

    void addLine(int values[]);

    void addFace(int vertexIndices[]);

    void addObjectName(String name);

    void addMapLib(String[] names);

    void setCurrentGroupNames(String[] names);

    void setCurrentSmoothingGroup(int groupNumber);

    void setCurrentUseMap(String name);

    void setCurrentUseMaterial(String name);

    void newMtl(String name);

    void setXYZ(int type, float x, float y, float z);

    void setRGB(int type, float r, float g, float b);

    void setIllum(int illumModel);

    void setD(boolean halo, float factor);

    void setNs(float exponent);

    void setSharpness(float value);

    void setNi(float opticalDensity);

    void setMapDecalDispBump(int type, String filename);

    void setRefl(int type, String filename);

    void doneParsingMaterial();

    void doneParsingObj(String filename);
}