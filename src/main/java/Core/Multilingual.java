package Core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ArtStyle on 23.01.2017.
 */
public class Multilingual {
    private Map<Integer, String> value = new HashMap<>();
    private final Integer DEFAULT_LANGUAGEID = 0;

    public Multilingual() {
    }

    public Multilingual(Map<Integer, String> value) {
        this.value = value;
    }

    public Multilingual(String text) {
        this.value.put(DEFAULT_LANGUAGEID, text);
    }

    public Multilingual(Integer languageId, String text) {
        this.value.put(languageId, text);
    }

    public String getValue(){
        return  this.value.get(0);
    }

    public String getValue(Integer languageId){
        return  this.value.get(languageId);
    }

    public void setValue(Integer languageId, String text){
        this.value.put(languageId, text);
    }

    public void setValue(String text){
        this.value.put(DEFAULT_LANGUAGEID, text);
    }
}
