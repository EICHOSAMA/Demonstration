package per.eicho.demo.ioc;

/**
 * <p>控制反转样例类</p>
 */
final class IoCSample {

    /** 卡车司机 */
    public static abstract class VehicleDriver {
        Vehicle vehicle;
    }

    /** DIY卡车司机 */
    public static final class DIYVehicleDriver extends VehicleDriver {

        DIYVehicleDriver() {
            /* 传统使用就需要大量new，此时开发者权力最大，拥有一切组件的选择权 */
            vehicle = new Vehicle();
            vehicle.wheel = new WheelImplByGoodyear();
            vehicle.engine = new EngineImplByHonda();
            vehicle.controlPanel = new ControlPanelImplByCaska();
        }
    }

    /** 品牌车卡车司机 */
    public static final class BrandVehicleDriver extends VehicleDriver {
        BrandVehicleDriver() {
            /* 库的使用者，通常对库内部依赖不了解，下放组件选择权给库的开发者。*/
            vehicle = BrandVehicle.newBrandVehicle();     
        }
    }

    /** 苦逼打工人卡车司机 */
    public static final class NormalVehicleDriver extends VehicleDriver {
        final Vehicle vehicle;

        NormalVehicleDriver(Vehicle vehicle) {
            /* 苦逼打工人无法自主选择使用哪种车，只能被动接受。 */
            this.vehicle = vehicle;
        }
    }

    
    public static class Vehicle {
        /** 轮胎 */
        Wheel wheel;

        /** 引擎 */
        Engine engine;

        /** 中控台 */
        ControlPanel controlPanel;

        /* 略过其他需要的组件，复杂的组件需要依赖更多的组件... */

        public Vehicle() {}
    }

    /** 品牌车 */
    public static class BrandVehicle extends Vehicle {

        /** 工厂方法：《推荐配置》《组装品牌车》 */
        public static Vehicle newBrandVehicle() {
            final Vehicle vehicle = new BrandVehicle();
            vehicle.wheel = new WheelImplByBrand(); // 依赖品牌自己的轮胎
            vehicle.engine = new EngineImplByBrand(); // 依赖品牌自己的引擎
            vehicle.controlPanel = new ControlPanelByBrand(); // 依赖品牌自己的中控
            return vehicle;
        }

        /* 某品牌自主实现的轮胎、引擎、中控等产品。 */
        private static final class WheelImplByBrand implements Wheel {}
        private static final class EngineImplByBrand implements Engine {}
        private static final class ControlPanelByBrand implements ControlPanel {}
    }

    static interface Wheel { /* 轮胎 */ }
    static interface Engine { /* 引擎 */ }
    static interface ControlPanel { /* 中控 */}

    static final class WheelImplByMichelin implements Wheel { /* 米其林轮胎 */}
    static final class WheelImplByGoodyear implements Wheel { /* 固特异轮胎 */}

    static final class EngineImplByHonda implements Engine { /* 本田发动机 */ }
    static final class EngineImplByBMW implements Engine { /* 宝马发动机 */ }

    static final class ControlPanelImplByCaska implements ControlPanel { /* 卡仕卡中控 */ }
    static final class ControlPanelImplByFlyAudio implements ControlPanel { /* 飞歌发动机 */ }

    private IoCSample() {}
}
