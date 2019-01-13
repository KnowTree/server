export default class ComponentState {
    constructor(props) {
        this.data = props;
    }

    getProperty(name) {
        return this.data[name];
    }
}