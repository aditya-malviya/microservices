import groovy.json.JsonOutput
import groovy.transform.Field
import groovy.json.JsonSlurper


node ('JenkinsSlave_UI')
{
    parameters
    {
        choice(choices: 'develop\nrelease\nQA\nmaster' ,
        description:'select the Branch Name' ,
        name:'Branch_Name')
    }

    if ("${Branch_Name}" == 'master')
        {
            Checkout()
            Build()
            Create_Image()
            image push()
			Deploytok8s()
        }
   

    else
        {
            quote()
        }
}

def Checkout()
{
    stage('Checkout')
    {
        checkout scm
    }
}


def Junit() 
{
    stage('Junit') 
    {
        sh "cd ${params.Service_Name} ; mvn  test"
    }
}

def Build() 
{
    stage('Build') 
    {
       sh 'cd authutil; mvn clean install'
       sh "cd ${params.Service_Name} ; mvn clean package -Dmaven.test.skip=true" 
    }
}
def Create_Image()
{
    stage('Create_Image')
    withCredentials([usernamePassword(credentialsId: 'nexus_prod', usernameVariable: 'NEXUS_USER' , passwordVariable: 'NEXUS_PASSWORD' )]) 
    {
        sh "sudo docker login -u ${NEXUS_USER} -p ${NEXUS_PASSWORD} $env.dev_nexus_ip"
        sh "cd $WORKSPACE/${params.Service_Name} ; sudo docker build --tag=${params.Service_Name} ."
    }
}

def Image_Push() 
{
    stage('Image_Push')
    {
   // 
            sh "sudo docker login -u ankit1111 -p miet@1234 "
            sh "sudo docker tag  service-70900 $env.dev_nexus_ip/service-70900"
            sh "sudo docker push $env.dev_nexus_ip/$params.Service_Name:latest"
        }
    }
}

def Deploytok8s()
{
    stage('DeploytoProd')
    {
      node ("ansible-slave01")
      {
            sh "cd /opt/; ansible-playbook ms.yml "
      }

    }
}


