# ETLMR
1.原始数据为"H-ucblRMjuY	nevrknw	735	People & Blogs	22	299935	2.67	640	213	9jEZyxFs1C0	PBvopqeKYlM	jiExVINzBzk	2d5fjIDafH4	-_gfF-hlGAA	LOdSctUe6Gg	xC8kNCiFcOc	uAaQdthRYEw	JNceFGdH-sE	ZHvv8Hs4QiA	4psD5FlJCn8	DRgJn4dEBXY	koKu8LV_c2c	xajrbuaOp2E	29taWVbP-uY	JoGJ6JW0GWs	LKxeWIcXpig	J7d01xFC2GY	Xtg8MJmYKjc	8TNeXmXMWBY"

2.原始数据第四个字段为集合，最后一个字段为集合，所有字段都是"\t"分割，第四个字段集合用" & "分割，最后一个字段集合用"\t"分割。

3.对原始数据进行清洗，去除第四个字段中的空格，第四个字段是一个集合，最后一个字段也是一个集合，集合统一用"&"做分隔符，字段与字段间用"\t"分割。
总共10个字段，最后一个字段允许为空，所以要过滤出小于9个字段的数据

4.清洗后的数据格式为"H-ucblRMjuY	nevrknw	735	People&Blogs	22	299935	2.67640	213	9jEZyxFs1C0	PBvopqeKYlM&jiExVINzBzk&2d5fjIDafH4&-_gfF-hlGAA&LOdSctUe6Gg&xC8kNCiFcOc&uAaQdthRYEw&JNceFGdH-sE&ZHvv8Hs4QiA&4psD5FlJCn8&DRgJn4dEBXY&koKu8LV_c2c&xajrbuaOp2E&29taWVbP-uY&JoGJ6JW0GWs&LKxeWIcXpig&J7d01xFC2GY&Xtg8MJmYKjc&8TNeXmXMWBY"

