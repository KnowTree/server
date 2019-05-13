import {React, List, AlertManager} from "CakeReact";
import {search, deleteEntity} from "../utils/ApiCall";
import {Modal} from "CakeReact";
import AddNodeView from "./AddNodeView";
import {withRouter, Link} from 'react-router-dom';

class KNodeList extends List {
    constructor() {
        super();
    }

    doFetch() {
        const option = {
            l: this.size(),
            s: this.startIndex(),
            cursor: this.cursor(),
        };
        const course_id = this.props.course_id;
        if (course_id) {
            search("Knode", {course_id: this.props.course_id}, option, (result) => this.setItems(result.data), error => this.setError(error));
        } else {
            this.setItems([]);
        }
    }

    renderHeader() {
        return (
            <div>
                <h4>Knodes</h4>
            </div>
        )
    }

    renderRow(item, index) {
        let editUrl = '/course/' + this.props.course_id + "/node/" + item.id;

        return (
            <div>
                <p>ID : {item.id}</p>
                <p>Title : {item.title}</p>
                <Link to={editUrl}>Edit</Link>
                <button onClick={this.deleteItem.bind(this ,item.id)}>Delete</button>

            </div>
        )
    }

    renderPager() {
        const start = this.startIndex();
        const size = this.size();
        const end = start + size;
        const msg = start + " - " + end;
        return (
            <div>
                {this.canPrevious() ? <button onClick={this.loadPrevious}> Prev </button> : ''}
                {msg}
                {this.canNext() ? <button onClick={this.loadNext}> Next </button> : ''}
            </div>
        )
    }

    toolbar() {
        const addUrl = "/course/" + this.props.course_id + "/new_node";
        return (
            <div>
                <Link to={addUrl}>Add</Link>
            </div>
        )
    }

    deleteItem(id) {
        deleteEntity("Knode", id, (result) => {
            this.doFetch();
        }, error => AlertManager.show('error', error.message));
    }
}

export default KNodeList;