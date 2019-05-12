import {React, Form, TextInput} from "CakeReact";
import {create, get, update} from "../utils/ApiCall";

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
            <div>
                <h4>Course Infomation</h4>
                <TextInput name="title" label="Title" form={this}/>
                <TextInput name="description" label="Description" form={this}/>
                {this.state.error ? <p>{this.state.error}</p> :''}
                <button onClick={this.submit}>Save</button>
                <button onClick={this.cancel}>Cancel</button>
            </div>
        )
    }

    doSubmit() {
        const values = this.data();
        if (this.props && this.props.course_id) {
            update("Course", this.props.course_id, values, (result)=>this.data(result), error=> this.error(error));
        } else {
            create("Course", values, (result) => {
                const {id} = result;
                if (id) {
                    //refresh
                    window.location("/course/" + id);
                }
            }, error => this.data({error: error}))
        }
    }

    onSubmitted() {

    }

    cancel() {
        window.location("/courses");
    }
}

export default CourseBasicInfoForm;