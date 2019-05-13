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
                <CourseBasicInfoForm isNew={true}/>
            </div>
        )
    }
}

export default AddCourseView;