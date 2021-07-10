package com.liuwq.hdfs.manage.pojo;

import org.apache.hadoop.fs.FileStatus;

import java.util.ArrayList;

public interface FileFormat {
    ArrayList<HdfsFile> format(FileStatus[] fileStatuses);
}
