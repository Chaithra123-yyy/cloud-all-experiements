package org.cloudbus.cloudsim.examples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

public class CloudSimExample3{

    public static void main(String[] args) {

        try {

            // STEP 1: Initialize CloudSim
            int numUsers = 1;

            Calendar calendar = Calendar.getInstance();

            boolean traceFlag = false;

            CloudSim.init(
                    numUsers,
                    calendar,
                    traceFlag
            );

            // STEP 2: Create Data Center
            Datacenter datacenter =
                    createDatacenter("Datacenter_0");

            // STEP 3: Create Broker
            DatacenterBroker broker =
                    new DatacenterBroker("Broker");

            int brokerId = broker.getId();

            // STEP 4: Create VMs
            List<Vm> vmList =
                    createVMs(brokerId);

            broker.submitVmList(vmList);

            // STEP 5: Create Cloudlets
            List<Cloudlet> cloudletList =
                    createCloudlets(brokerId);

            // STEP 6: Apply NEW algorithm
            List<Cloudlet> scheduledCloudlets =
                    shortestCloudletFirst(cloudletList);

            // Display scheduling order
            System.out.println(
                    "\nSCF Scheduling Order:"
            );

            for (Cloudlet cloudlet : scheduledCloudlets) {

                System.out.println(
                        "Cloudlet "
                                + cloudlet.getCloudletId()
                                + " - Length: "
                                + cloudlet.getCloudletLength()
                );
            }

            // STEP 7: Submit cloudlets
            broker.submitCloudletList(
                    scheduledCloudlets
            );

            // STEP 8: Start simulation
            CloudSim.startSimulation();

            // STEP 9: Get results
            List<Cloudlet> resultList =
                    broker.getCloudletReceivedList();

            CloudSim.stopSimulation();

            // STEP 10: Print results
            System.out.println(
                    "\n========== RESULTS =========="
            );

            System.out.println(
                    "Cloudlet\tStatus\tVM\tStart\tFinish"
            );

            for (Cloudlet cloudlet : resultList) {

                System.out.println(
                        cloudlet.getCloudletId()
                                + "\t\t"
                                + cloudlet.getCloudletStatusString()
                                + "\t"
                                + cloudlet.getVmId()
                                + "\t"
                                + cloudlet.getExecStartTime()
                                + "\t"
                                + cloudlet.getFinishTime()
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================
    // CREATE DATA CENTER
    // =====================================

    private static Datacenter createDatacenter(
            String name) throws Exception {

        List<Host> hostList =
                new ArrayList<Host>();

        // Create CPU
        List<Pe> peList =
                new ArrayList<Pe>();

        int mips = 1000;

        peList.add(
                new Pe(
                        0,
                        new PeProvisionerSimple(mips)
                )
        );

        // Create Physical Host
        Host host =
                new Host(
                        0,
                        new RamProvisionerSimple(2048),
                        new BwProvisionerSimple(10000),
                        1000000,
                        peList,
                        new VmSchedulerTimeShared(peList)
                );

        hostList.add(host);

        // Data Center characteristics
        String arch = "x86";
        String os = "Linux";
        String vmm = "Xen";

        double timeZone = 10.0;
        double cost = 3.0;
        double costPerMem = 0.05;
        double costPerStorage = 0.001;
        double costPerBw = 0.0;

        LinkedList<Storage> storageList =
                new LinkedList<Storage>();

        DatacenterCharacteristics characteristics =
                new DatacenterCharacteristics(
                        arch,
                        os,
                        vmm,
                        hostList,
                        timeZone,
                        cost,
                        costPerMem,
                        costPerStorage,
                        costPerBw
                );

        // Create Data Center
        return new Datacenter(
                name,
                characteristics,
                new VmAllocationPolicySimple(hostList),
                storageList,
                0
        );
    }

    // =====================================
    // CREATE VMs
    // =====================================

    private static List<Vm> createVMs(
            int brokerId) {

        List<Vm> vmList =
                new ArrayList<Vm>();

        int mips = 1000;
        int ram = 512;
        long bw = 1000;
        long size = 10000;
        int pesNumber = 1;
        String vmm = "Xen";

        for (int i = 0; i < 2; i++) {

            Vm vm =
                    new Vm(
                            i,
                            brokerId,
                            mips,
                            pesNumber,
                            ram,
                            bw,
                            size,
                            vmm,
                            new CloudletSchedulerTimeShared()
                    );

            vmList.add(vm);
        }

        return vmList;
    }

    // =====================================
    // CREATE CLOUDLETS
    // =====================================

    private static List<Cloudlet> createCloudlets(
            int brokerId) {

        List<Cloudlet> cloudletList =
                new ArrayList<Cloudlet>();

        long[] lengths = {
                8000,
                2000,
                5000,
                1000,
                7000,
                3000
        };

        int pesNumber = 1;
        long fileSize = 300;
        long outputSize = 300;

        UtilizationModel utilizationModel =
                new UtilizationModelFull();

        for (int i = 0;
             i < lengths.length;
             i++) {

            Cloudlet cloudlet =
                    new Cloudlet(
                            i,
                            lengths[i],
                            pesNumber,
                            fileSize,
                            outputSize,
                            utilizationModel,
                            utilizationModel,
                            utilizationModel
                    );

            cloudlet.setUserId(brokerId);

            cloudletList.add(cloudlet);
        }

        return cloudletList;
    }

    // =====================================
    // NEW SCHEDULING ALGORITHM
    // SHORTEST CLOUDLET FIRST
    // =====================================

    private static List<Cloudlet> shortestCloudletFirst(
            List<Cloudlet> cloudlets) {

        List<Cloudlet> sortedCloudlets =
                new ArrayList<Cloudlet>(cloudlets);

        Collections.sort(
                sortedCloudlets,
                new Comparator<Cloudlet>() {

                    @Override
                    public int compare(
                            Cloudlet c1,
                            Cloudlet c2) {

                        return Long.compare(
                                c1.getCloudletLength(),
                                c2.getCloudletLength()
                        );
                    }
                }
        );

        return sortedCloudlets;
    }
}