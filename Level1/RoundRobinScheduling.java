class Process {
    int pid;
    int burstTime;
    int remainingTime;
    int priority;
    Process next;

    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

public class RoundRobinScheduling {
    private Process head = null;
    private Process tail = null;
    private int timeQuantum;

    public RoundRobinScheduling(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (head == null) {
            head = newProcess;
            tail = newProcess;
            newProcess.next = head;
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
    }

    public void removeProcess(int pid) {
        if (head == null) return;

        Process current = head;
        Process prev = tail;

        do {
            if (current.pid == pid) {
                if (current == head) head = head.next;
                if (current == tail) tail = prev;
                prev.next = current.next;
                if (current == current.next) {
                    head = null;
                    tail = null;
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    public void simulate() {
        if (head == null) return;

        int time = 0;
        Process current = head;
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;
        int n = 0;

        while (head != null) {
            if (current.remainingTime > 0) {
                int execTime = Math.min(timeQuantum, current.remainingTime);
                current.remainingTime -= execTime;
                time += execTime;

                System.out.println("Executing Process " + current.pid + " for " + execTime + " units. Remaining: " + current.remainingTime);

                if (current.remainingTime == 0) {
                    int turnAroundTime = time;
                    int waitingTime = turnAroundTime - current.burstTime;
                    totalTurnAroundTime += turnAroundTime;
                    totalWaitingTime += waitingTime;
                    n++;
                    System.out.println("Process " + current.pid + " completed. TAT: " + turnAroundTime + ", WT: " + waitingTime);
                    Process toRemove = current;
                    current = current.next;
                    removeProcess(toRemove.pid);
                    displayProcesses();
                    continue;
                }
            }
            current = current.next;
        }

        System.out.println("Average Waiting Time: " + (float) totalWaitingTime / n);
        System.out.println("Average Turn-Around Time: " + (float) totalTurnAroundTime / n);
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        Process current = head;
        System.out.print("Current Queue: ");
        do {
            System.out.print("[PID: " + current.pid + ", RT: " + current.remainingTime + "] -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(Back to Head)");
    }

    public static void main(String[] args) {
        RoundRobinScheduling scheduler = new RoundRobinScheduling(4);

        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 4, 1);
        scheduler.addProcess(3, 6, 3);

        scheduler.simulate();
    }

    public int getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
}
