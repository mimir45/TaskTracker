import { TaskPriority } from "./TaskPriority.ts";
import { TaskStatus } from "./TaskStatus.ts";

interface Task {
  id: string | undefined;
  title: string;
  description: string;
  dueDate: Date | undefined;
  priority: TaskPriority;
  status: TaskStatus | undefined;
}
export default Task;
