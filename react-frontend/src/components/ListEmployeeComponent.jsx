import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';

class ListEmployeeComponent extends Component {

    constructor(props) {

        super(props)

        this.state = {
            employees: []
        }

        this.addEmployee = this.addEmployee.bind(this);
        this.editEmployee = this.editEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
        this.viewEmployee = this.viewEmployee.bind(this);
    }

    viewEmployee(id){
        this.props.history.push(`/view-employee/${id}`);
    }

    deleteEmployee(id){
        console.log(id);

        EmployeeService.deleteEmployee(id).then((res) => {

           // this.props.history.push('/employees');

           this.setState({employees: this.state.employees.filter(employee => employee.id !== id)});

        });
        
        
       
    }

    editEmployee(id){
        this.props.history.push(`/update-employee/${id}`);
    }

    componentDidMount(){
        EmployeeService.getEmployees().then((res) => {

            this.setState({employees: res.data});

        })
    }

    addEmployee(){
        this.props.history.push('/add-employee');
    }

    render() {
        return (
            <div>

                <h2 className="text-center">Colaboradores</h2>

                <div className="row">

                    <table className="table table-striped table-bordered"> 

                       <thead>

                            <tr>
                                <th>Nome</th>
                                <th>Sobrenome</th>
                                <th>Email</th>
                                <th>Ações</th>
                            </tr>

                       </thead>

                       <tbody>

                        {
                            this.state.employees.map(
                                employee => 
                                <tr key ={employee.id}>

                                    <td>{employee.firstName}</td>
                                    <td>{employee.lastName}</td>
                                    <td>{employee.emailId}</td>
                                    <td>

                                        <button onClick={() => this.editEmployee(employee.id)} className="btn btn-warning">Atualizar</button>
                                        <button style ={{marginLeft: "10px"}}onClick={() => this.deleteEmployee(employee.id)} className="btn btn-danger">Apagar</button>
                                        <button style ={{marginLeft: "10px"}}onClick={() => this.viewEmployee(employee.id)} className="btn btn-info">Detalhes</button>
                                    </td>
                                </tr>
                            )
                        }

                       </tbody>

                       

                    </table>

                    

                </div>
                <div className = "row">

                    <button className="btn btn-primary" onClick={this.addEmployee}>Adicionar Colaborador</button>

                </div>

            </div>
        );
    }
}

export default ListEmployeeComponent;