package repository;

import java.util.LinkedHashMap;
import java.util.Map;

//	存取字典資料庫
public class TransferDao {

	//	模擬一個字典資料庫
	private static Map<String, String> dict = new LinkedHashMap<>();
	static {
		dict.put("pen", "筆");
		dict.put("book", "書");
		dict.put("apple", "蘋果");
		dict.put("筆", "pen");
		dict.put("書", "book");
		dict.put("蘋果", "apple");
	
	}
	
	//	取得所有字典資料
	public  Map<String, String> queryAll(){
		return dict;
	}
	
	//	根據 word word取出翻譯結果
	public String getByWord(String word) {
		return dict.get(word);
	}
	
}
