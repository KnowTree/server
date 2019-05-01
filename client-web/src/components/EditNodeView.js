import React from 'react';
import Form from "../widgets/form/Form";
import TextInput from "../widgets/form/TextInput";
import {get, update} from "../utils/ApiCall";
class EditNodeView extends Form {
    render() {
        return (
            <div>
                {this.state.isNew ? '' : <p>{this.state.data.id}</p>}
                <TextInput name="title"/>
                <TextInput name="url"/>
            </div>
        )
    }

    doFetch() {
        const {node_id, course_id} = this.props.match.params;
        get("Knode", node_id, null, (result) => {
            this.data(result);
        }, error => this.error(error));

    }

    doSubmit() {
        update('Knode', this.state.data.id, this.data(), (result) => {}, error => {});
    }
}

export default EditNodeView;