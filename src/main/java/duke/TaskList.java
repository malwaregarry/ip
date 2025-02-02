package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * A class that handles logging of Tasks.
 */
public class TaskList {
    private static final String STRING_FORMAT_OUT_OF_BOUNDS = "Index %d out of bounds. There are only %d tasks";

    private final List<Task> logs;

    /**
     * Constructor for TaskList.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        logs = new ArrayList<>(tasks);
    }

    /**
     * Constructor for TaskList without an initial list of tasks.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Returns number of tasks in TaskList.
     *
     * @return Number of tasks.
     */
    public int size() {
        return this.logs.size();
    }

    /**
     * Adds task to list.
     *
     * @param task Task to add.
     * @return Task which was added.
     */
    public Task add(Task task) {
        logs.add(task);
        return task;
    }

    /**
     * Deletes task at specified index from list.
     *
     * @param index Index of task to delete.
     * @return Task which was deleted
     * @throws DukeException when index specified is out of range.
     */
    public Task delete(int index) throws DukeException {
        try {
            return logs.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
    }

    /**
     * Returns task at specified index.
     *
     * @param index Index of task.
     * @return Task at specified index.
     * @throws DukeException when index specified is out of range.
     */
    Task getTask(int index) throws DukeException {
        try {
            return logs.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
    }

    /**
     * Marks task at index as complete.
     *
     * @param index Index of task to mark.
     * @return Updated Task that was marked.
     * @throws DukeException when index specified is out of range.
     */
    public Task markTask(int index) throws DukeException {
        try {
            getTask(index).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
        return getTask(index);
    }

    /**
     * Marks task at index as incomplete.
     *
     * @param index Index of task to unmark.
     * @return Updated Task that was unmarked.
     * @throws DukeException when index specified is out of range.
     */
    public Task unmarkTask(int index) throws DukeException {
        try {
            getTask(index).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
        return getTask(index);
    }

    /**
     * Updates description of task at specified index.
     *
     * @param index Index of task to update.
     * @param updatedDescription New description of task.
     * @return Updated Task.
     * @throws DukeException When index specified is out of range.
     */
    public Task updateTask(int index, String updatedDescription) throws DukeException {
        try {
            getTask(index).setDescription(updatedDescription);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(outOfBoundsMessage(index), e);
        }
        return getTask(index);
    }

    /**
     * Returns a list of tasks in the TaskList.
     * Each string is prefixed with their corresponding index in the list.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return logs;
    }

    /**
     * Returns a list of tasks that matches the query text.
     *
     * @return List of matched tasks.
     */
    public List<Task> find(String query) {
        return this.logs.stream()
                .filter((task) -> task.getDescription().contains(query))
                .collect(Collectors.toList());
    }

    private String outOfBoundsMessage(int index) {
        return String.format(STRING_FORMAT_OUT_OF_BOUNDS, (convertZeroToOneIndex(index)), logs.size());
    }

    private int convertZeroToOneIndex(int index) {
        return index + 1;
    }

    @Override
    public String toString() {
        return getTasks().toString();
    }
}
