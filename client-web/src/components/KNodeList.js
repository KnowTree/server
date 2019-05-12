import {React, List, AlertManager} from "CakeReact";
import {search, deleteEntity} from "../utils/ApiCall";
import {Modal} from "CakeReact";
import AddNodeView from "./AddNodeView";
import {withRouter, Link} from 'react-router-dom';

class KNodeList extends List {
    constructor() {
        super();
        this.state.showAddView = false;
        this.openAddView = this.openAddView.bind(this);
    }
   doFetch() {
       const option = {
           l : this.size(),
           s : this.startIndex(),
           cursor : this.cursor(),
       };
       const course_id = this.props.course_id;
       if (course_id) {
        search("Knode", {course_id : this.props.course_id}, option, (result) => this.setItems(result.data), error => this.setError(error));
       } else {
           this.setItems([]);
       }
   }

   renderHeader() {
       return (
           <h4>Knodes</h4>
       )
   }

   renderRow(item, index) {
       let editUrl = '/course/' + this.props.course_id +  "/node/"+item.id;

       return (
           <div>
               <p>ID : {item.id}</p>
               <p>Title : {item.title}</p>
               <Link to={editUrl}>Edit</Link>
               <button onClick={this.deleteItem}>Delete</button>

           </div>
       )
   }

   renderPager() {

   }

   toolbar() {
       const addUrl = "/course/" + this.props.course_id + "/new_node";
       return (
           <div>
               <button onClick={this.openAddView}>Add</button>
               <Modal isOpen={this.state.showAddView}>
                   <AddNodeView course_id={this.props.course_id}
                                parent={this}/>
               </Modal>
           </div>
       )
   }

   deleteItem(e) {
       const index = e.target.data;
       const item = this.state.items[index];
       deleteEntity("node", item.id, (result) => {
           this.doFetch();
       }, error => this.setState({error : error}))
   }

   openAddView() {
        this.setState({showAddView : true});
   }

   onCancel() {
        this.setState({showAddView : false});

   }

   onSubmitted(item) {
        let currentItem = this.state.items;
        currentItem.push(item);
       this.setState({
           showAddView : false,
           items : currentItem,
       });
   }
}

export default KNodeList;