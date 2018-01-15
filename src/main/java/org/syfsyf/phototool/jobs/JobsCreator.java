package org.syfsyf.phototool.jobs;

import org.syfsyf.phototool.DataModel;

import java.util.List;

/**
 * Created by mb on 2018-01-15.
 */
public interface JobsCreator {

    List<Job> create(DataModel model) throws Exception;

}
