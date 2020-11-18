package entity;

import entity.Part;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Dongfanger
 * @date 2020/11/16
 */
@Data
@Component
public class Quartet {
    Part violin;
    Part viola;
    Part cello;
}
