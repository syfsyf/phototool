package org.syfsyf.phototool;

import org.syfsyf.phototool.domain.JobSetupDto;
import org.syfsyf.phototool.gui.JobsStatusDto;

import java.io.FileNotFoundException;

public interface PhotoolFacade {

    void process(DataModel dataModel, JobsStatus viewModel) throws InterruptedException;
    void runJob(JobSetupDto jobSetupDto,JobsStatus jobsStatus) throws PhototoolException;
    JobSetupDto getJobSetupDto() throws PhototoolException;



}
