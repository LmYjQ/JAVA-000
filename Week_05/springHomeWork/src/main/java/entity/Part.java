package entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Dongfanger
 * @date 2020/11/16
 */
@Data
@Component
public class Part {
    @Autowired
    List<MusicianImp> musicianList;

    public void zhi(){
        System.out.println(this.getMusicianList());
    }
}
