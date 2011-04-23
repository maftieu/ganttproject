/*
 * Created on 05.07.2003
 *
 */
package net.sourceforge.ganttproject.task;

import java.util.Date;
import java.util.Map;

import net.sourceforge.ganttproject.CustomPropertyManager;
import net.sourceforge.ganttproject.GanttTask;
import net.sourceforge.ganttproject.calendar.GPCalendar;
import net.sourceforge.ganttproject.gui.options.model.StringOption;
import net.sourceforge.ganttproject.resource.HumanResource;
import net.sourceforge.ganttproject.resource.HumanResourceManager;
import net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade.Factory;
import net.sourceforge.ganttproject.task.algorithm.AlgorithmCollection;
import net.sourceforge.ganttproject.task.dependency.TaskDependencyCollection;
import net.sourceforge.ganttproject.task.dependency.TaskDependencyConstraint;
import net.sourceforge.ganttproject.task.event.TaskListener;
import net.sourceforge.ganttproject.time.TimeUnit;

/**
 * @author bard
 */
public interface TaskManager {
    Task[] getTasks();

    public Task getRootTask();

    void projectOpened();

    public void projectClosed();

    public GanttTask getTask(int taskId);

    public void registerTask(Task task);

    public GanttTask createTask();

    public GanttTask createTask(int taskId);

    String encode(TaskLength duration);
    TaskLength createLength(String lengthAsString);

    public TaskLength createLength(long length);

    TaskLength createLength(TimeUnit unit, float length);

    public TaskLength createLength(TimeUnit timeUnit, Date startDate,
            Date endDate);

    Date shift(Date original, TaskLength duration);
    TaskDependencyCollection getDependencyCollection();

    AlgorithmCollection getAlgorithmCollection();

    TaskDependencyConstraint createConstraint(int constraintID);
    TaskDependencyConstraint createConstraint(TaskDependencyConstraint.Type constraintType);
    GPCalendar getCalendar();

    TaskContainmentHierarchyFacade getTaskHierarchy();

    void addTaskListener(TaskListener listener);

    public class Access {
        public static TaskManager newInstance(
                TaskContainmentHierarchyFacade.Factory containmentFacadeFactory,
                TaskManagerConfig config) {
            return new TaskManagerImpl(containmentFacadeFactory, config,null);
        }

        public static TaskManager newInstance(
                Factory factory,
                TaskManagerConfig taskConfig,
                CustomColumnsStorage customColumnsStorage) {
            return new TaskManagerImpl(factory, taskConfig, customColumnsStorage);
        }
    }

    public TaskLength getProjectLength();

    public int getTaskCount();

    public Date getProjectStart();
    public Date getProjectEnd();
    int getProjectCompletion();

    public TaskManager emptyClone();

    public Map<Task, Task> importData(TaskManager taskManager);

    public void importAssignments(TaskManager importedTaskManager,
    		HumanResourceManager hrManager, Map<Task, Task> original2importedTask,
            Map<HumanResource, HumanResource> original2importedResource);

    /**
     * Processes the critical path finding on <code>root</code> tasks.
     *
     * @param root
     *            The root of the tasks to consider in the critical path
     *            finding.
     */
    @Deprecated
    public void processCriticalPath(TaskNode root);
    public void processCriticalPath(Task root);
    
    public void deleteTask(Task tasktoRemove);

    CustomColumnsStorage getCustomColumnStorage();
    CustomPropertyManager getCustomPropertyManager();
    
    StringOption getTaskNamePrefixOption();
}
