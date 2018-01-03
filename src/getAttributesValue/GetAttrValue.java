package getAttributesValue;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class GetAttrValue {
	public static void main(String[] args) {

		ArrayList<String[]> list = new ArrayList<String[]>();
		BlackInfoRequestOut testmodel = new BlackInfoRequestOut();
		testmodel.setCardNo("22");
		testmodel.setToken("545");
		list = ConvertObjToMap(testmodel);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]+","+list.get(i)[1]);
		}
		/*
		 * Map<String, String> map = new HashMap<String, String>(); for (String
		 * key : map.keySet()) { System.out.println("key= "+ key +
		 * " and value= " + map.get(key)); }
		 */
	}

	public static ArrayList<String[]> ConvertObjToMap(Object obj) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				try {
					String attrName = fields[i].getName();
					Field f = obj.getClass().getDeclaredField(attrName);
					f.setAccessible(true);
					Object o = f.get(obj);
					list.add(new String[]{attrName,o.toString()});
				} catch (NoSuchFieldException e) {
					System.out.println("NoSuchFieldException");
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					System.out.println("IllegalArgumentException");
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					System.out.println("IllegalAccessException");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
