package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author Dongfanger
 * @date 2020/11/16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class MusicianImp implements Musician {

    private int id;
    private String name;

    @Override
    public void play(){
        System.out.println("playing");
    }

    public MusicianImp create(){
        return new MusicianImp(101,"KK101");
    }
}
