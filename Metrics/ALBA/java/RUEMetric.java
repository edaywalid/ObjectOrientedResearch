import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class RUEMetric {
    private long pid;

    public  RUEMetric() {
        // Get PID of the current Java process
        this.pid = ProcessHandle.current().pid();
    }

    public double getCPUUsage() {
        try (BufferedReader reader = new BufferedReader(new FileReader("/proc/" + pid + "/stat"))) {
            String line = reader.readLine();
            if (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                // Skip irrelevant fields
                for (int i = 1; i < 14; i++) {
                    tokenizer.nextToken();
                }
                // Extract CPU time spent in user mode and kernel mode
                long utime = Long.parseLong(tokenizer.nextToken());
                long stime = Long.parseLong(tokenizer.nextToken());
                long totalCpuTime = utime + stime;

                // Read total elapsed time since system boot
                long uptime = readUptime();

                // Calculate CPU usage percentage
                return 100.0 * totalCpuTime / uptime;
            } else {
                throw new IllegalStateException("Error: Unable to read CPU usage");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0; // Return 0 if unable to retrieve CPU usage
        }
    }

    private long readUptime() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader("/proc/uptime"))) {
            String line = reader.readLine();
            if (line != null) {
                return (long) Double.parseDouble(line.split("\\s+")[0]);
            } else {
                throw new IllegalStateException("Error: Unable to read system uptime");
            }
        }
    }

    public static void main(String[] args) {
        RUEMetric metric = new RUEMetric();
        System.out.println("CPU Usage: " + metric.getCPUUsage() + "%");
    }

}