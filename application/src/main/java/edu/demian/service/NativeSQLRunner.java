package edu.demian.service;

import edu.demian.dao.NativeSQL;
import edu.demian.models.JobExecutionStatus;
import java.util.UUID;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NativeSQLRunner {
  private static int MAX_RUM = 10;

  private final NativeSQL nativeSQL;

  public NativeSQLRunner(NativeSQL nativeSQL) {
    this.nativeSQL = nativeSQL;
  }

  @Async
  public void startTest() {
    nativeSQL.createConnection();
    for (int i = 0; i < MAX_RUM; i++) {
      JobExecutionStatus job = new JobExecutionStatus();
      job.setJobId(UUID.randomUUID().toString());
      job.setJobName("InstanceCreationJob");
      job.setResult("");
      job.setStartTime(System.currentTimeMillis());
      job.setStatus("In Progress");

      nativeSQL.insertJob(job);

      job.setResult("Instance creation successfully completed");
      job.setStatus("Succeeded");

      nativeSQL.updateJob(job);

      nativeSQL.getJob(job.getJobId());
    }
    nativeSQL.closeConnection();
  }
}