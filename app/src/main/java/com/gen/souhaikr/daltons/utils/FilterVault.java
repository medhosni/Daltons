/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.util.ArrayList
 */
package com.gen.souhaikr.daltons.utils;

import com.gen.souhaikr.daltons.utils.Filter;
import com.gen.souhaikr.daltons.utils.UniformPair;
import java.util.ArrayList;

public class FilterVault {
    private static final String basics = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\n";
    private static final String blue = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(0.0, 0.0, tex.b, tex.a);}";
    private static final String cb_d = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst vec2 rcoeff = vec2(0.625, 0.375);const vec2 gcoeff = vec2(0.7, 0.3);const vec2 bcoeff = vec2(0.3, 0.7);void main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  float r2 =  dot(tex.rg, rcoeff);  float g2 =  dot(tex.rg, gcoeff);  float b2 =  dot(tex.gb, bcoeff);  gl_FragColor = vec4(r2, g2, b2, tex.a);}";
    private static final String cb_daltonize_d = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst vec2 rcoeff = vec2(0.885, 0.115);const vec3 bcoeff = vec3(-0.49, 0.19, 1.3);void main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  float r2 =  dot(tex.rg, rcoeff);  float b2 =  dot(tex.rgb, bcoeff);  gl_FragColor = vec4(r2, tex.g, b2, tex.a);}";
    private static final String cb_daltonize_p = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst vec2 gcoeff = vec2(-0.255, 1.255);const vec3 bcoeff = vec3(0.30333, -0.545, 1.2417);void main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  float g2 =  dot(tex.rg, gcoeff) ;  float b2 =  dot(tex.rgb, bcoeff);  gl_FragColor = vec4(tex.r , g2 , b2, tex.a);}";
    private static final String cb_daltonize_t = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst vec3 rcoeff = vec3(1.05, -0.3825, 0.3325);const vec2 gcoeff = vec2(1.2342, -0.23417);void main() {  vec4 tex = texture2D( s_texture, textureCoordinate ); float r2 =  dot(tex.rgb, rcoeff);  float g2 =  dot(tex.gb, gcoeff);  gl_FragColor = vec4( r2, g2, tex.ba);}";
    private static final String cb_p = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst vec2 rcoeff = vec2(0.56667, 0.43333);const vec2 gcoeff = vec2(0.55833, 0.44167);const vec2 bcoeff = vec2(0.24167, 0.75833);void main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  float r2 =  dot(tex.rg, rcoeff);  float g2 =  dot(tex.rg, gcoeff);  float b2 =  dot(tex.gb, bcoeff);  gl_FragColor = vec4(r2, g2, b2, tex.a);}";
    private static final String cb_t = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst vec2 rcoeff = vec2(0.95, 0.05);const vec2 gcoeff = vec2(0.43333, 0.56667);const vec2 bcoeff = vec2(0.475, 0.525);void main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  float r2 =  dot(tex.rg, rcoeff);  float g2 =  dot(tex.gb, gcoeff);  float b2 =  dot(tex.gb, bcoeff);  gl_FragColor = vec4(r2, g2, b2, tex.a);}";
    private static String conv_HSV_RGB = "const vec4 Khsv = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);const vec4 Krgb = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);const float e2 = 1.0e-10;vec3 rgb2hsv(vec3 c){    vec4 p = mix(vec4(c.bg, Krgb.wz), vec4(c.gb, Krgb.xy), step(c.b, c.g));    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));    float d = q.x - min(q.w, q.y);    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e2)), d / (q.x + e2), q.x);}vec3 hsv2rgb(vec3 c){    vec3 p = abs(fract(c.xxx + Khsv.xyz) * 6.0 - Khsv.www);    return c.z * mix(Khsv.xxx, clamp(p - Khsv.xxx, 0.0, 1.0), c.y);}";
    private static String dimensionFactors = "uniform mediump float imageWidthFactor;uniform mediump float imageHeightFactor;";
    public static final String distortB = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(tex.rg, 1.0-(tex.b-0.5)*(tex.b-0.5), tex.a);}";
    public static final String distortG = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(tex.r, 1.0-(tex.g-0.5)*(tex.g-0.5), tex.ba);}";
    public static final String distortR;
    private static String extractLocals;
    private static final String fragmentShaderCode = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  gl_FragColor = texture2D( s_texture, textureCoordinate );\n}";
    public static String fs_GrayCCIR;
    private static final String gray = "const vec3 graycoeff = vec3(0.299, 0.587, 0.114);";
    private static final String green = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(0.0, tex.g, 0.0, tex.a);}";
    public static final String greenFlash;
    public static final String inverted = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(vec3(1.0)-tex.rgb, tex.a);}";
    public static final String invpermuteBGR = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = 1.-vec4(tex.bgra);}";
    public static final String invpermuteBRG = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = 1.-vec4(tex.brga);}";
    public static final String invpermuteGBR = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = 1.-vec4(tex.gbra);}";
    public static final String invpermuteGRB = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = 1.-vec4(tex.grba);}";
    public static final String invpermuteRBG = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = 1.-vec4(tex.rbga);}";
    private static String localScaledVectors;
    private static String localVectors;
    public static final String magentaFlash;
    private static String magneto;
    private static String matrix;
    private static final String noblue = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(tex.r, tex.g, 0.0, tex.a);}";
    private static final String nogreen = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(tex.r, 0.0, tex.b, tex.a);}";
    private static final String nored = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nvoid main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(0.0, tex.gba);}";
    private static final String[] officialNames;
    private static final String[] officialShaders;
    private static String[] uniformDataNames;
    private static float[] uniformDataValues;
    public static final String yellowFlash;
    public final String blueFlash;
    private String cartoonFlash;
    public final String cyanFlash;
    private String digitalRain;
    private String invertedSobelEdge;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst float e = 2.718;");
        stringBuilder.append(conv_HSV_RGB);
        stringBuilder.append("void main() {  vec4 tex = texture2D( s_texture, textureCoordinate );  gl_FragColor = vec4(hsv2rgb(rgb2hsv(tex.rgb)), tex.a);}");
        distortR = stringBuilder.toString();
        extractLocals = "mediump vec3 textureColor = texture2D(s_texture, textureCoordinate).rgb;mediump float i00   = dot( textureColor, W);mediump float im1m1 = dot( texture2D(s_texture, textureCoordinate - stpp).rgb, W);mediump float ip1p1 = dot( texture2D(s_texture, textureCoordinate + stpp).rgb, W);mediump float im1p1 = dot( texture2D(s_texture, textureCoordinate - stpm).rgb, W);mediump float ip1m1 = dot( texture2D(s_texture, textureCoordinate + stpm).rgb, W);mediump float im10 = dot( texture2D(s_texture, textureCoordinate - stp0).rgb, W);mediump float ip10 = dot( texture2D(s_texture, textureCoordinate + stp0).rgb, W);mediump float i0m1 = dot( texture2D(s_texture, textureCoordinate - st0p).rgb, W);mediump float i0p1 = dot( texture2D(s_texture, textureCoordinate + st0p).rgb, W);mediump float h = -im1p1 - 2.0 * i0p1 - ip1p1 + im1m1 + 2.0 * i0m1 + ip1m1;mediump float v = -im1m1 - 2.0 * im10 - im1p1 + ip1m1 + 2.0 * ip10 + ip1p1;mediump float mag = 1.0 - length(vec2(h, v));";
        StringBuilder stringBuilder2 = new StringBuilder(basics);
        stringBuilder2.append(gray);
        stringBuilder2.append("void main() {");
        stringBuilder2.append("  vec4 tex = texture2D( s_texture, textureCoordinate );");
        stringBuilder2.append("if(length(tex.rgb-vec3(0,1,0)) < 0.7){");
        stringBuilder2.append("  gl_FragColor = vec4(tex);");
        stringBuilder2.append("}");
        stringBuilder2.append("else{");
        stringBuilder2.append("  float c = dot(tex.rgb, graycoeff);");
        stringBuilder2.append("  gl_FragColor = vec4(vec3(c), tex.a);");
        stringBuilder2.append("}");
        stringBuilder2.append("}");
        greenFlash = stringBuilder2.toString();
        localScaledVectors = "mediump vec2 stp0 = vec2(factor*imageWidthFactor, 0.0);mediump vec2 st0p = vec2(0.0, factor*imageHeightFactor);mediump vec2 stpp = vec2(factor*imageWidthFactor, factor*imageHeightFactor);mediump vec2 stpm = vec2(factor*imageWidthFactor, -factor*imageHeightFactor);";
        localVectors = "mediump vec2 stp0 = vec2(imageWidthFactor, 0.0);mediump vec2 st0p = vec2(0.0, imageHeightFactor);mediump vec2 stpp = vec2(imageWidthFactor, imageHeightFactor);mediump vec2 stpm = vec2(imageWidthFactor, -imageHeightFactor);";
        StringBuilder stringBuilder3 = new StringBuilder(basics);
        stringBuilder3.append(gray);
        stringBuilder3.append("void main() {");
        stringBuilder3.append("  vec4 tex = texture2D( s_texture, textureCoordinate );");
        stringBuilder3.append("if(length(tex.rgb-vec3(1,0,1)) < .8){");
        stringBuilder3.append("  gl_FragColor = vec4(tex);");
        stringBuilder3.append("}");
        stringBuilder3.append("else{");
        stringBuilder3.append("  float c = dot(tex.rgb, graycoeff);");
        stringBuilder3.append("  gl_FragColor = vec4(vec3(c), tex.a);");
        stringBuilder3.append("}");
        stringBuilder3.append("}");
        magentaFlash = stringBuilder3.toString();
        StringBuilder stringBuilder4 = new StringBuilder(basics);
        stringBuilder4.append(gray);
        stringBuilder4.append("const mediump float center = 0.5;");
        stringBuilder4.append("uniform mediump float targetX;");
        stringBuilder4.append("uniform mediump float targetY;");
        stringBuilder4.append("const mediump float radius = 0.001;");
        stringBuilder4.append("void main() {");
        stringBuilder4.append("  mediump vec4 textureColor = texture2D(s_texture, textureCoordinate);");
        stringBuilder4.append("  mediump float grayscale = dot(textureColor.rgb, graycoeff);");
        stringBuilder4.append("  mediump float diffX = targetX - textureCoordinate.x;");
        stringBuilder4.append("  mediump float diffY = targetY - textureCoordinate.y;");
        stringBuilder4.append("  if(diffX*diffX + diffY*diffY < radius){");
        stringBuilder4.append("    gl_FragColor = vec4(1.0-textureColor.rgb, 1.);");
        stringBuilder4.append("  }else{");
        stringBuilder4.append("    gl_FragColor = textureColor;}");
        stringBuilder4.append("}");
        magneto = stringBuilder4.toString();
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst mediump float intensity = 0.5;");
        stringBuilder5.append(dimensionFactors);
        stringBuilder5.append("const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);const mediump float MagTol = 0.95;const mediump float Quantize = 5.0;void main() {");
        stringBuilder5.append(localVectors);
        stringBuilder5.append(extractLocals);
        stringBuilder5.append("  if(mag > MagTol){    gl_FragColor = vec4(vec3(0.0),1.);  }else{gl_FragColor = vec4(0.0,0.797,0.0, 1.);}}");
        matrix = stringBuilder5.toString();
        officialNames = new String[]{"No Filter", "Protanopia Simulator", "Protanopia Corrected", "Deuteranopia Simulator", "Deuteranopia Corrected", "Tritanopia Simulator", "Tritanopia Corrected"};
        officialShaders = new String[]{fragmentShaderCode, cb_p, cb_daltonize_p, cb_d, cb_daltonize_d, cb_t, cb_daltonize_t};
        uniformDataNames = new String[]{"imageWidthFactor", "imageHeightFactor"};
        uniformDataValues = new float[]{10.0f, 10.0f};
        StringBuilder stringBuilder6 = new StringBuilder(basics);
        stringBuilder6.append(gray);
        stringBuilder6.append("void main() {");
        stringBuilder6.append("  vec4 tex = texture2D( s_texture, textureCoordinate );");
        stringBuilder6.append("if(length(tex.rgb-vec3(1.,1.,0.)) < .7){");
        stringBuilder6.append("  gl_FragColor = vec4(tex);");
        stringBuilder6.append("}");
        stringBuilder6.append("else{");
        stringBuilder6.append("  float c = dot(tex.rgb, graycoeff);");
        stringBuilder6.append("  gl_FragColor = vec4(vec3(c), tex.a);");
        stringBuilder6.append("}");
        stringBuilder6.append("}");
        yellowFlash = stringBuilder6.toString();
        StringBuilder stringBuilder7 = new StringBuilder(basics);
        stringBuilder7.append(gray);
        stringBuilder7.append("void main() {");
        stringBuilder7.append("  vec4 tex = texture2D( s_texture, textureCoordinate );");
        stringBuilder7.append("  float c = dot(tex.rgb, graycoeff);");
        stringBuilder7.append("  gl_FragColor = vec4(vec3(c), tex.a);");
        stringBuilder7.append("}");
        fs_GrayCCIR = stringBuilder7.toString();
    }

    public FilterVault() {
        StringBuilder stringBuilder = new StringBuilder(basics);
        stringBuilder.append(gray);
        stringBuilder.append("void main() {");
        stringBuilder.append("  vec4 tex = texture2D( s_texture, textureCoordinate );");
        stringBuilder.append("if(length(tex.rgb-vec3(0,0,1)) < .7){");
        stringBuilder.append("  gl_FragColor = vec4(tex);");
        stringBuilder.append("}");
        stringBuilder.append("else{");
        stringBuilder.append("  float c = dot(tex.rgb, graycoeff);");
        stringBuilder.append("  gl_FragColor = vec4(vec3(c), tex.a);");
        stringBuilder.append("}");
        stringBuilder.append("}");
        this.blueFlash = stringBuilder.toString();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst mediump float intensity = 0.5;");
        stringBuilder2.append(dimensionFactors);
        stringBuilder2.append("uniform mediump float irand;uniform mediump float jrand;uniform mediump float krand;const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);const mediump float MagTol = 0.1;const mediump float Quantize = 3.0;void main() {");
        stringBuilder2.append(localVectors);
        stringBuilder2.append(extractLocals);
        stringBuilder2.append("  mediump vec3 target = vec3(mag);  gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);  if(mag > MagTol){    textureColor *= Quantize;    textureColor += vec3(.5);    ivec3 intrgb = ivec3(textureColor);    textureColor = vec3(intrgb)/Quantize;  if(length(textureColor - vec3(irand,jrand,krand) ) < 0.3  ){    gl_FragColor = vec4(textureColor, 1.);    }else{    gl_FragColor = vec4(1.);  }}else{gl_FragColor = vec4(vec3(0.0), 1.);}}");
        this.cartoonFlash = stringBuilder2.toString();
        StringBuilder stringBuilder3 = new StringBuilder(basics);
        stringBuilder3.append(gray);
        stringBuilder3.append("void main() {");
        stringBuilder3.append("  vec4 tex = texture2D( s_texture, textureCoordinate );");
        stringBuilder3.append("if(length(tex.rgb-vec3(0,1,1)) < .7){");
        stringBuilder3.append("  gl_FragColor = vec4(tex);");
        stringBuilder3.append("}");
        stringBuilder3.append("else{");
        stringBuilder3.append("  float c = dot(tex.rgb, graycoeff);");
        stringBuilder3.append("  gl_FragColor = vec4(vec3(c), tex.a);");
        stringBuilder3.append("}");
        stringBuilder3.append("}");
        this.cyanFlash = stringBuilder3.toString();
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst mediump float intensity = 0.5;");
        stringBuilder4.append(dimensionFactors);
        stringBuilder4.append("const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);const mediump float MagTol = 0.5;const mediump float Quantize = 5.0;uniform mediump float time;void main() {");
        stringBuilder4.append(localVectors);
        stringBuilder4.append(extractLocals);
        stringBuilder4.append("  mediump vec3 target = vec3(mag);  gl_FragColor = vec4(mix(textureColor, target, intensity), 1.0);  if(mag > MagTol && abs(sin(dot(textureColor.rg ,textureCoordinate.xy) *5000.0*time)) <0.99){    gl_FragColor = vec4(vec3(0.0), 1.);  }else{    gl_FragColor = vec4(vec3(0.494, 0.589, 0.623), 1.);}}");
        this.digitalRain = stringBuilder4.toString();
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append("#extension GL_OES_EGL_image_external : require\nprecision mediump float;varying vec2 textureCoordinate;\nuniform samplerExternalOES s_texture;\nconst mediump float intensity = 1.0;");
        stringBuilder5.append(dimensionFactors);
        stringBuilder5.append("const mediump vec3 W = vec3(0.2125, 0.7154, 0.0721);void main() {");
        stringBuilder5.append(localVectors);
        stringBuilder5.append(extractLocals);
        stringBuilder5.append("mediump vec3 target = vec3(mag);gl_FragColor = vec4(vec3(1,1,1)-mix(textureColor, target, intensity), 1.0);}");
        this.invertedSobelEdge = stringBuilder5.toString();
    }

    public static ArrayList<Filter> getAllFilters() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < officialNames.length; ++i) {
            arrayList.add((Object)new Filter(officialNames[i], officialShaders[i]));
        }
        return arrayList;
    }

    public static String[] getAllNames() {
        return officialNames;
    }

    public static ArrayList<UniformPair> getAllUniformPairs() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < uniformDataNames.length; ++i) {
            arrayList.add((Object)new UniformPair(uniformDataNames[i], uniformDataValues[i]));
        }
        return arrayList;
    }

    public static int getNumFilters() {
        return officialNames.length;
    }

    public static void updateUniformValues(float[] arrf) {
        for (int i = 0; i < arrf.length; ++i) {
            FilterVault.uniformDataValues[i] = arrf[i];
        }
    }
}

