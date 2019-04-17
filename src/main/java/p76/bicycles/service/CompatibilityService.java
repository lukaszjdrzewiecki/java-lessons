package p76.bicycles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import p76.bicycles.db.entity.Bicycle;
import p76.bicycles.db.entity.Cassette;
import p76.bicycles.db.entity.FrontWheel;
import p76.bicycles.db.repository.BicycleRepository;

import java.util.*;

import static p76.bicycles.service.Messages.*;

@Component
public class CompatibilityService {

    @Autowired
    BicycleRepository repository;

    @Autowired
    BicycleService service;

    private int drivetrainCapacity(Bicycle bicycle) {
        return (bicycle.getCassette().getMaximum() - bicycle.getCassette().getMinimum())
                + (bicycle.getCrank().getBigGear() - bicycle.getCrank().getSmallGear());
    }

    public Boolean drivetrainCheck(Bicycle bicycle) {
        try {
            if (bicycle.getRearDerailleur().getCapacity() >= drivetrainCapacity(bicycle)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return null;
        }
    }

    public void drivetrainCheckPrint(Bicycle bicycle) {
        if (drivetrainCheck(bicycle)) {
            System.out.println(DRIVETRAIN_CHECK + COMPATIBLE_ALL);
        } else {
            System.out.println(DRIVETRAIN_CHECK + COMPATIBLE_NOT);
            System.out.println(DRIVETRAIN_CHECK + DRIVETRAIN_EXPLANATION + (bicycle.getRearDerailleur().getCapacity() - drivetrainCapacity(bicycle) + bicycle.getCassette().getMaximum()));
        }
    }

    public void changeCasette(Bicycle bicycle, Cassette cassette) {
        System.out.println(CHANGING_COMPONENT);
        bicycle.setCassette(cassette);
        drivetrainCheckPrint(bicycle);
    }

    Boolean speedsCompatibilityCheck(Bicycle bicycle) {
        try {
            if (allEqual(bicycle.getCassette().getSpeed(), bicycle.getRearDerailleur().getSpeed(), bicycle.getCrank().getSpeed())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return null;
        }
    }

    public void speedsCompatibilityCheckPrint(Bicycle bicycle) {
        if (speedsCompatibilityCheck(bicycle)) {
            System.out.println(NUMBER_SPEEDS_CHECK + COMPATIBLE_ALL);
        } else {
            System.out.println(NUMBER_SPEEDS_CHECK + COMPATIBLE_NOT);
        }
    }

    boolean allEqual(int a, int... integers) {
        for (int i : integers) {
            if (!(i == a)) {
                return false;
            }
        }
        return true;
    }

    public boolean wheelDiameterCheck(Bicycle bicycle) {
        if (allEqual(bicycle.getFrontWheel().getRim().getDiameter(), bicycle.getFrontWheel().getTyre().getDiameter())) {
            return true;
        }
        return false;
    }

    public boolean wheelHolesCheck(Bicycle bicycle) {
        if (allEqual(bicycle.getFrontWheel().getRim().getHoles(), bicycle.getFrontWheel().getHub().getHoles())) {
            return true;
        }
        return false;
    }

    public Boolean wheelCheck (Bicycle bicycle) {
        try {
            if (wheelDiameterCheck(bicycle) && wheelHolesCheck(bicycle)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return null;
        }
    }

    public void wheelCheckPrint(Bicycle bicycle) {
        System.out.println(WHEEL_CHECK);
        if (wheelDiameterCheck(bicycle)) {
            System.out.println(DIAMETER + COMPATIBLE_ALL);
        } else {
            System.out.println(DIAMETER + COMPATIBLE_NOT);
        }
        if (wheelHolesCheck(bicycle)) {
            System.out.println(NUMBER_OF_HOLES + COMPATIBLE_ALL);
        } else {
            System.out.println(NUMBER_OF_HOLES + COMPATIBLE_NOT + ". RIM: " + bicycle.getFrontWheel().getRim().getHoles() + ", HUB: " + bicycle.getFrontWheel().getHub().getHoles());
        }
    }

    public List<CompatibilityResult> bicycleCheck(Bicycle bicycle) {
        List<CompatibilityResult> result = new ArrayList();
        result.add(new CompatibilityResult("drivetrainCheck", drivetrainCheck(bicycle)));
        result.add(new CompatibilityResult("speedsCompatibilityCheck", speedsCompatibilityCheck(bicycle)));
        result.add(new CompatibilityResult("wheelCheck", wheelCheck(bicycle)));
        return result;
    }

    public List<List<CompatibilityResult>> bicycleCheckAll(){
        List<List<CompatibilityResult>> result = new ArrayList<>();

        List <Bicycle> bicycles = service.findAllBicycles();
        for (Bicycle bicycle : bicycles){
            result.add(bicycleCheck(bicycle));
        }

        return result;
    }

    public void rimTyreComatibilityCheckPrint(Bicycle bicycle) {
        int min = tyreRimRange(bicycle.getFrontWheel()).get(0);
        int max = tyreRimRange(bicycle.getFrontWheel()).get(1);
        if (rimTyreCompatibilityCheck(bicycle.getFrontWheel())) {
            System.out.println(RIM_TYRE_CHECK + COMPATIBLE_ALL);
        } else {
            System.out.println(RIM_TYRE_CHECK + COMPATIBLE_NOT + ". Your rim width equals " + bicycle.getFrontWheel().getRim().getInnerWidth() + " mm and thus recommended size of a tyre is " + min + " - " + max + " mm");
        }
    }

    public boolean rimTyreCompatibilityCheck(FrontWheel wheel) {
        int tyre = wheel.getTyre().getWidth();
        List<Integer> rangeList = tyreRimRange(wheel);
        int min = rangeList.get(0);
        int max = rangeList.get(1);
        boolean flag = (tyre >= min && tyre <= max);
        return flag;
    }

    public List<Integer> tyreRimRange(FrontWheel wheel) {
        int rim = wheel.getRim().getInnerWidth();
        int temp = 0;
        for (int key : diameterMap().keySet()) {
            if (rim == key) {
                temp = key;
            }
        }
        List<Integer> tempList = diameterMap().get(temp);
        return tempList;
    }

    private Map<Integer, List<Integer>> diameterMap() {
        Map<Integer, List<Integer>> diameters = new HashMap<>();
        diameters.put(13, new ArrayList<>(Arrays.asList(18, 25)));
        diameters.put(15, new ArrayList<>(Arrays.asList(23, 32)));
        diameters.put(17, new ArrayList<>(Arrays.asList(25, 52)));
        diameters.put(19, new ArrayList<>(Arrays.asList(28, 62)));
        diameters.put(21, new ArrayList<>(Arrays.asList(35, 62)));
        diameters.put(23, new ArrayList<>(Arrays.asList(37, 62)));
        diameters.put(25, new ArrayList<>(Arrays.asList(42, 62)));
        diameters.put(29, new ArrayList<>(Arrays.asList(47, 62)));
        diameters.put(30, new ArrayList<>(Arrays.asList(52, 62)));
        diameters.put(35, new ArrayList<>(Arrays.asList(60, 75)));
        diameters.put(40, new ArrayList<>(Arrays.asList(65, 75)));
        diameters.put(45, new ArrayList<>(Arrays.asList(70, 75)));
        return diameters;
    }
}
