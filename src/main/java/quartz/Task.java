package quartz;

/**
 * Created by viccardo on 4/02/16.
 */
public interface Task<INPUT> {

    void execute(INPUT input);
}
