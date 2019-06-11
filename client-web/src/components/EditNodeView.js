import {React, Form, TextInput, HiddenInput, NumberInput} from "CakeReact";
import {get, update} from "../utils/ApiCall";
import {AlertManager, BrowserUtils} from 'CakeReact';


class EditNodeView extends Form {
    constructor(props) {
        super(props);
        this.state.isNew = false;
    }

    render() {

        return (
            <div>
                {this.state.isNew ? '' : <p>{this.state.data.id}</p>}
                <TextInput name="title" form={this} label="Title"/>
                <TextInput name="url" form={this} label="URL"/>
                <NumberInput name="order" label="Order" form={this}/>
                <HiddenInput name="course_id" form={this}/>
                {!this.state.isNew ? <HiddenInput name="id" form={this}/> : ''}
                <button onClick={this.submit}>Save</button>
                <button onClick={this.cancel}>Cancel</button>
            </div>
        )

    }

    doFetch() {
        const {node_id, course_id} = this.props.params;
        get("Knode", node_id, null, (result) => {
            this.data(result);
        }, error => this.error(error));

    }

    doSubmit() {
        update('Knode', this.state.data.id, this.data(), (result) => {
            this.cancel();
        }, error => {
            AlertManager.show("error", error.message);
        });
    }

    cancel() {
        const {node_id, course_id} = this.props.params;
        let backTo = "/course/" + course_id;
        BrowserUtils.changeUrlAndEmitEvent(backTo);
    }
}

export default EditNodeView;