/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.examples.compute.snippets;

import com.google.cloud.compute.Compute;
import com.google.cloud.compute.ComputeOptions;
import com.google.cloud.compute.Disk;
import com.google.cloud.compute.DiskId;
import com.google.cloud.compute.Operation;
import com.google.cloud.compute.Snapshot;

/**
 * A snippet for Google Cloud Compute Engine showing how to create a snapshot of a disk if the disk
 * exists.
 */
public class CreateSnapshot {

  public static void main(String... args) throws InterruptedException {
    Compute compute = ComputeOptions.defaultInstance().service();
    DiskId diskId = DiskId.of("us-central1-a", "disk-name");
    Disk disk = compute.getDisk(diskId, Compute.DiskOption.fields());
    if (disk != null) {
      String snapshotName = "disk-name-snapshot";
      Operation operation = disk.createSnapshot(snapshotName);
      while (!operation.isDone()) {
        Thread.sleep(1000L);
      }
      if (operation.errors() == null) {
        // use snapshot
        Snapshot snapshot = compute.getSnapshot("disk-name-snapshot");
      }
    }
  }
}
