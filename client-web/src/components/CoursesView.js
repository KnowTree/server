import {Link} from "react-router-dom";
import {React, List, AlertManager} from "CakeReact";
import {search, deleteEntity} from "../utils/ApiCall";

class CoursesView extends List {
    doFetch() {
        const option = {
            l : this.size(),
            s : this.startIndex(),
            cursor : this.cursor()
        };
        search("Course", {}, option,
                result => this.setItems(result.data),
                error => AlertManager.show('error', error.message));
    }

    renderHeader() {
        return (
            <h3>Courses</h3>
        )
    }

    renderRow(item, index) {
        const editUrl = "/course/" + item.id;
        return (
            <div>
                <p>ID : {item.id}</p>
                <p>Title : {item.title}</p>
                <Link to={editUrl}>Edit</Link>
                <button onClick={this.deleteItem.bind(this, item.id)}>Delete</button>
            </div>
        )
    }

    renderPager() {
        return (
            <div>
                {this.canPrevious() ? <button onClick={this.loadNext}>Previous</button> : <button disabled={true}>Previous</button>}
                {this.canNext() ? <button onClick={this.loadPrevious}>Next</button> : <button disabled={true}>Next</button>}
            </div>
        )
    }

    toolbar() {
        return (
            <div>
                <Link to="/new_course">Add</Link>
            </div>
        )
    }

    deleteItem(id) {
        deleteEntity("Course", id,
            (result) => this.doFetch(),
                error => AlertManager.show('error', error.message));
    }
}

export default CoursesView;