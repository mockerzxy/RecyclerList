package zxy.studio.testtransition;

import android.app.NotificationManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xueyuanzhang on 17/9/12.
 */

public class Note {
    public String expandText;
    public String foldText;
    public boolean isExpand;

    public static List<Note> create(){
        List<Note> list = new ArrayList<>();
        for(int i= 0;i<15;i++){
            Note note = new Note();
            note.expandText = "expand " + i;
            note.foldText = "fold "+ i;
            list.add(note);
        }
        return list;
    }
}
