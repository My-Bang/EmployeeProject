package StudentManagementRefactor;

import java.io.IOException;
import java.util.HashMap;

public abstract class ObjectIO {
    abstract HashMap<String, Student> loadData() throws IOException;

}
