import EditNodeView from "./EditNodeView";
import {create} from "../utils/ApiCall";
import {React, AlertManager} from "CakeReact";
class AddNodeView extends EditNodeView {
    constructor() {
        super();
        this.state.isNew = true;
    }

    doFetch() {

    }

    doSubmit(onSubmittedFunc) {
        let data = this.data();
        data.course_id = this.props.params.course_id;
        create("Knode", data, result => {
            this.cancel();
        }, error => {
            AlertManager.show("error", error.message);
        });
    }
}

export default AddNodeView;