import {React, Form, TextInput, MultiSelectInput, NumberInput, AlertManager, HiddenInput} from "CakeReact";
import {create, get, update} from "../utils/ApiCall";
import {BrowserUtils} from "CakeReact";

class CourseBasicInfoForm extends Form {
    constructor() {
        super();
        this.cancel = this.cancel.bind(this);
    }

    doFetch() {
        if (this.props && this.props.course_id) {
            get("Course", this.props.course_id, null, (result) => this.data(result), error => this.error(error));
        }
    }

    render() {
        return (
            <div className="card">
                <div className="card-body">
                    <h5>Course Information</h5>
                    <TextInput name="title" label="Title" form={this}/>
                    <TextInput name="author" label="Author" form={this}/>
                    <MultiSelectInput name="categories" label="Categories" form={this}/>
                    <NumberInput name="price" label="Price" form={this}/>
                    {this.props.isNew ? '' :<HiddenInput name="id" form={this}/>}
                    {this.state.error ? <p>{this.state.error}</p> : ''}
                    <button role="form-submit-button" onClick={this.submit}>Save</button>
                    <button  role="form-cancel-button" onClick={this.cancel}>Cancel</button>
                </div>
            </div>
        )
    }

    doSubmit() {
        const values = this.data();
        if (this.props && this.props.course_id) {
            update("Course", this.props.course_id, values,
                (result)=> {AlertManager.show('info', "Updated"); this.data(result)},
                    error=> AlertManager.show('error', error.message));
        } else {
            create("Course", values, (result) => {
                const {id} = result;
                const path = "/course/" + id;
                BrowserUtils.changeUrlAndEmitEvent(path);
            }, error => AlertManager.show('error', error.message))
        }
    }

    cancel() {
        BrowserUtils.changeUrlAndEmitEvent("/courses");
    }
}

export default CourseBasicInfoForm;