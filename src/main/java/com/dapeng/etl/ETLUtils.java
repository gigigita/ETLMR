package com.dapeng.etl;

public class ETLUtils {
    public static String etlstr(String str){
        String string="H-ucblRMjuY\tnevrknw\t735\tPeople & Blogs\t22\t299935\t2.67640\t213\t9jEZyxFs1C0\tPBvopqeKYlM\tjiExVINzBzk\t2d5fjIDafH4\t-_gfF-hlGAA\tLOdSctUe6Gg\txC8kNCiFcOc\tuAaQdthRYEw\tJNceFGdH-sE\tZHvv8Hs4QiA\t4psD5FlJCn8\tDRgJn4dEBXY\tkoKu8LV_c2c\txajrbuaOp2E\t29taWVbP-uY\tJoGJ6JW0GWs\tLKxeWIcXpig\tJ7d01xFC2GY\tXtg8MJmYKjc\t8TNeXmXMWBY\n";
        String[] splits = str.split("\t");
        if(splits.length<9)  return null;
        splits[3]=splits[3].replaceAll(" ","");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < splits.length; i++) {
            if(i<9){
                if(i==splits.length-1) {
                    stringBuffer.append(splits[i]);
                }else{
                    stringBuffer.append(splits[i]+"\t");
                }
            }else {
                if(i==splits.length-1) {
                    stringBuffer.append(splits[i]);
                }else{
                    stringBuffer.append(splits[i]+"&");
                }
            }
        }
        return stringBuffer.toString();
    }

}
