import EditNodeView from "./EditNodeView";
import {create} from "../utils/ApiCall";
import {React} from "CakeReact";

class AddNodeView extends EditNodeView {
    constructor() {
        super();
        this.state.isNew = true;
    }

    doFetch() {

    }

    doSubmit(onSubmittedFunc) {
        let data = this.data();
        data.course_id = this.props.course_id;
        create("Knode", data, result => {
            onSubmittedFunc(result);
        }, error => {});
    }

    cancel() {
        if (this.props.parent) {
            this.props.parent.onCancel();
        }
    }

    onSubmitted(data) {
        if (this.props.parent) {
            this.props.parent.onSubmitted(data);
        }
    }
}

export default AddNodeView;