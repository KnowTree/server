import {React} from 'CakeReact';
import CourseView from "./CourseView";
import CourseBasicInfoForm from "./CourseBasicInfoForm"

class AddCourseView extends CourseView {
    constructor() {
        super();
    }

    render() {
        return (
            <div className="center-box card">
                <h5>Add Course</h5>
                <CourseBasicInfoForm isNew={true}/>
            </div>
        )
    }
}

export default AddCourseView;