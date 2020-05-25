package cc.akali.edu_service.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dan
 * @Date: 2020/5/22 15:16
 * @Description:
 */
@Data
public class OneSubject {
    private String id;
    private  String title;
    private List<TwoSubject> children = new ArrayList<>();
}
