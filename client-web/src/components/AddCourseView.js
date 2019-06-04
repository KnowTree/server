import {React} from 'CakeReact';
import CourseView from "./CourseView";
import CourseBasicInfoForm from "./CourseBasicInfoForm"

class AddCourseView extends CourseView {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <h3>Add Course</h3>
                <div className="col-md-6">
                <CourseBasicInfoForm isNew={true}/>
                </div>
            </div>
        )
    }
}

export default AddCourseView;