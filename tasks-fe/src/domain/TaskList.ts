import Task from "./Task.ts";

interface TaskList {
  id: string | undefined;
  title: string;
  description: string | undefined;
  count: number | undefined;
  progress: number | undefined;
  tasks: Task[] | undefined;
}
export default TaskList;
