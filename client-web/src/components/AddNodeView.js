import React from 'react';
import EditNodeView from "./EditNodeView";
import {create} from "../utils/ApiCall";

class AddNodeView extends EditNodeView {
    constructor() {
        super();
        this.state.isNew = true;
    }

    doFetch() {

    }

    doSubmit() {
        create("Knode", this.data(), result => {}, error => {});
    }
}

export default AddNodeView;